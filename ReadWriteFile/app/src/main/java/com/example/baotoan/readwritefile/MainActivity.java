package com.example.baotoan.readwritefile;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnWriteSD, btnWriteInternal, btnRead, btnDelete;
    private ImageView showImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWriteSD = (Button) findViewById(R.id.btn_write_sd);
        btnWriteInternal = (Button) findViewById(R.id.btn_write_internal);
        btnRead = (Button) findViewById(R.id.btn_read);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        showImage = (ImageView) findViewById(R.id.show_image);

        btnWriteSD.setOnClickListener(this);
        btnWriteInternal.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int component = v.getId();
        if(component == btnWriteSD.getId()) {
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.io);
            IOUtils.writeImageToSD(bitmap, "iconExtends.png", this);
        } else if(component == btnWriteInternal.getId()) {
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.io);
            IOUtils.writeImageToInternal(bitmap, "iconInternal.png", this);
        } else if(component == btnRead.getId()) {
            Bitmap image = IOUtils.readImage("iconInternal.png", this);
            showImage.setImageBitmap(image);
        } else {
            IOUtils.deleteImage("iconExtends.png", this);
            IOUtils.deleteImage("iconInternal.png", this);
            Bitmap image = IOUtils.readImage("iconInternal.png", this);
            showImage.setImageBitmap(image);
        }
    }
}
