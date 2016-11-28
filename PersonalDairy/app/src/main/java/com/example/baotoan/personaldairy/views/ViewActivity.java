package com.example.baotoan.personaldairy.views;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.baotoan.personaldairy.R;
import com.example.baotoan.personaldairy.controllers.ImageViewPageAdapter;
import com.example.baotoan.personaldairy.controllers.NoteDataSource;
import com.example.baotoan.personaldairy.models.NoteModel;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private TextView lblViewContent;
    private ViewPager imagePage;

    private NoteDataSource noteDataSource;
    private Bundle bundle;

    private static final int REQUEST_CODE_WRITE_NOTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        noteDataSource = new NoteDataSource(this);
        noteDataSource.open();

        // GET data
        bundle = getIntent().getExtras();

        toolBar = (Toolbar) findViewById(R.id.view_tool_bar);
        imagePage = (ViewPager) findViewById(R.id.image_page);
        lblViewContent = (TextView) findViewById(R.id.lbl_view_content);

        // Set toolbar
        toolBar.setTitle(bundle.getString("TITLE"));
        toolBar.setSubtitle(bundle.getString("DATETIME"));
        setSupportActionBar(toolBar);
        // Set image page
        updateImagePage(bundle);
        // Set content
        lblViewContent.setText(bundle.getString("CONTENT"));
    }

    private void updateImagePage(Bundle bundle) {
        Log.i("images", bundle.getString("IMAGES"));
        if(bundle.getString("IMAGES").trim().length() > 0) {
            ImageViewPageAdapter imageViewPageAdapter = new ImageViewPageAdapter(this, new ArrayList(Arrays.asList(bundle.getString("IMAGES").split(","))));
            imagePage.setAdapter(imageViewPageAdapter);
            imagePage.setVisibility(View.VISIBLE);
        } else {
            imagePage.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, WriteNoteActivity.class);
        switch (item.getItemId()) {
            case R.id.mn_view_delete:
                noteDataSource.deleteNote(bundle.getInt("ID"));
                this.finish();
                return true;
            case R.id.mn_view_edit:
                intent.putExtra("ACTION", "EDIT");
                intent.putExtra("TITLE", bundle.getString("TITLE"));
                intent.putExtra("CONTENT", bundle.getString("CONTENT"));
                intent.putExtra("STATUS", bundle.getString("STATUS"));
                intent.putExtra("IMAGES", bundle.getString("IMAGES"));
                intent.putExtra("ID", bundle.getInt("ID"));
                intent.putExtra("DATETIME", bundle.getString("DATETIME"));
                break;
            case R.id.mn_view_add:
                intent.putExtra("ACTION", "ADD");
                break;
        }
        startActivityForResult(intent, REQUEST_CODE_WRITE_NOTE);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_WRITE_NOTE && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            toolBar.setTitle(bundle.getString("TITLE"));
            toolBar.setSubtitle(bundle.getString("DATETIME"));
            lblViewContent.setText(bundle.getString("CONTENT"));
            updateImagePage(bundle);
        }
    }
}
