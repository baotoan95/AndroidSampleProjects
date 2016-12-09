package com.example.baotoan.animationsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnFadeIn, btnFadeOut, btnZoomIn,
            btnZoomOut, btnRepeat, btnMoveUp, btnMoveDown,
            btnBack, btnRotate, btnSequence, btnSameTime;
    private ImageView imageView;
    private Animation animFadeIn, animFadeOut, aniZoomIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFadeIn = (Button) findViewById(R.id.btn_fade_in);
        btnFadeOut = (Button) findViewById(R.id.btn_fade_out);
        btnZoomIn = (Button) findViewById(R.id.btn_zoom_in);
        btnZoomOut = (Button) findViewById(R.id.btn_zoom_out);
        btnRepeat = (Button) findViewById(R.id.btn_repeat);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnRotate = (Button) findViewById(R.id.btn_rotate);
        btnMoveUp = (Button) findViewById(R.id.btn_move_up);
        btnMoveDown = (Button) findViewById(R.id.btn_move_down);
        btnSequence = (Button) findViewById(R.id.btn_sequence);
        btnSameTime = (Button) findViewById(R.id.btn_same_time);
        imageView = (ImageView) findViewById(R.id.image_view);

        btnFadeIn.setOnClickListener(this);
        btnFadeOut.setOnClickListener(this);
        btnZoomIn.setOnClickListener(this);
        btnZoomOut.setOnClickListener(this);
        btnRepeat.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnMoveUp.setOnClickListener(this);
        btnMoveDown.setOnClickListener(this);
        btnSequence.setOnClickListener(this);
        btnSameTime.setOnClickListener(this);

        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);
    }

    @Override
    public void onClick(View view) {
        if(view == btnFadeIn) {
            imageView.startAnimation(animFadeIn);
        } else if(view == btnFadeOut) {
            imageView.startAnimation(animFadeOut);
        }
    }
}
