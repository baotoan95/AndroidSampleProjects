package com.example.baotoan.personaldairy.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baotoan.personaldairy.R;
import com.example.baotoan.personaldairy.controllers.ImageSlideAdapter;
import com.example.baotoan.personaldairy.controllers.NoteDataSource;
import com.example.baotoan.personaldairy.models.NoteModel;
import com.example.baotoan.personaldairy.utils.Config;
import com.example.baotoan.personaldairy.utils.Utils;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

public class WriteNoteActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private EditText edtTitle;
    private EditText edtContent;
    private ViewPager viewPager;

    private String mode;
    private NoteDataSource noteDataSource;

    public static final int RESULT_CODE_PICK_IMAGE = 1;
    private ArrayList<Bitmap> images = new ArrayList<>();
    private ArrayList<String> imageNames = new ArrayList<>();

    private NoteModel noteModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);

        noteDataSource = new NoteDataSource(this);
        noteDataSource.open();

        toolBar = (Toolbar) findViewById(R.id.write_tool_bar);
        edtTitle = (EditText) findViewById(R.id.edt_title);
        edtContent = (EditText) findViewById(R.id.edt_content);
        viewPager = (ViewPager) findViewById(R.id.image_slider);

        Bundle bundle = getIntent().getExtras();
        mode = bundle.getString("ACTION");
        if(mode.equals("EDIT")) {
            noteModel = new NoteModel();
            noteModel.setId(bundle.getInt("ID"));
            noteModel.setTitle(bundle.getString("TITLE"));
            noteModel.setContent(bundle.getString("CONTENT"));
            noteModel.setStatus(bundle.getString("STATUS"));
            noteModel.setDatetime(bundle.getString("DATETIME"));
            noteModel.setImages(bundle.getString("IMAGES"));

            // Update images array
            String[] imgs = bundle.getString("IMAGES").split(",");
            for(int i = 0; i < imgs.length; i++) {
                if(imgs[i].length() > 0) {
                    imageNames.add(imgs[i]);
                    try {
                        images.add(Utils.getImageFromMemory(this, Config.IMAGE_FOLDER, imgs[i]));
                    } catch (Exception e) {
                    }
                }
            }

            // Set for components
            edtTitle.setText(noteModel.getTitle());
            edtContent.setText(noteModel.getContent());
        }
        // Set toolbar
        toolBar.setTitle(noteModel != null ? noteModel.getTitle() : "Add new note");
        toolBar.setSubtitle(noteModel != null ? noteModel.getDatetime() : "Give me your mind");
        setSupportActionBar(toolBar);

        updateImageSlider();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noteDataSource.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.write_tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_write_attach:
                pickImage();
                break;
            case R.id.mn_write_save:
                String title = edtTitle.getText().toString().trim();
                if(title.trim().length() > 0) {
                    NoteModel newNote = new NoteModel();
                    newNote.setStatus(NoteModel.COL_ANGRY);
                    newNote.setTitle(title);
                    newNote.setContent(edtContent.getText().toString().trim());
                    newNote.setDatetime(Utils.getCurrentTime());
                    newNote.setImages(saveImages(images));
                    if(mode.equals("ADD")) {
                        noteDataSource.addNote(newNote);
                    } else {
                        newNote.setId(noteModel.getId());
                        noteDataSource.updateNote(newNote);
                    }
                    Intent intent = new Intent();
                    intent.putExtra("TITLE", newNote.getTitle());
                    intent.putExtra("CONTENT", newNote.getContent());
                    intent.putExtra("STATUS", newNote.getStatus());
                    intent.putExtra("IMAGES", newNote.getImages());
                    intent.putExtra("ID", newNote.getId());
                    intent.putExtra("DATETIME", newNote.getDatetime());
                    setResult(Activity.RESULT_OK, intent);
                    this.finish();
                } else {
                    Toast.makeText(this, "Please input title before", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    private String saveImages(ArrayList<Bitmap> arrImages) {
        String result = "";
        for(int i = 0; i < images.size(); i++) {
            result += imageNames.get(i) + ",";
            Bitmap image = images.get(i);
            try {
                Utils.saveImageToSDCard(image, Config.IMAGE_FOLDER, imageNames.get(i));
            } catch (Exception e) {}
        }
        return result.substring(0, (result.length() - 1) > 0 ? (result.length() -1) : 0);
    }

    private void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Select your pictures"), RESULT_CODE_PICK_IMAGE);
    }

    private void updateImageSlider() {
        if(images.size() == 0) {
            viewPager.setVisibility(View.GONE);
        } else {
            viewPager.setAdapter(new ImageSlideAdapter(this, images));
            viewPager.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RESULT_CODE_PICK_IMAGE:
                    try {
                        String imageName = Utils.convertDatetimeToFileName(Utils.getCurrentTime()) + ".png";
                        imageNames.add(imageName);
                        images.add(BitmapFactory.decodeStream(new BufferedInputStream(this.getContentResolver().openInputStream(data.getData()))));
                        updateImageSlider();
                        Toast.makeText(this, "You just attached a picture", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
