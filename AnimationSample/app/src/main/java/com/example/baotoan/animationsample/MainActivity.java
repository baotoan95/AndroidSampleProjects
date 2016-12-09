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
            btnMoveLeft, btnMoveRight, btnRotate, btnSequence, btnSameTime;
    private ImageView imageView;
    private Animation animFadeIn, animFadeOut, animZoomIn,
            animZoomOut, animRepeat, animMoveLeft, animMoveRight, animRotate,
            animMoveUp, animMoveDown, animSequence, animSameTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFadeIn = (Button) findViewById(R.id.btn_fade_in);
        btnFadeOut = (Button) findViewById(R.id.btn_fade_out);
        btnZoomIn = (Button) findViewById(R.id.btn_zoom_in);
        btnZoomOut = (Button) findViewById(R.id.btn_zoom_out);
        btnRepeat = (Button) findViewById(R.id.btn_repeat);
        btnMoveLeft = (Button) findViewById(R.id.btn_move_left);
        btnMoveRight = (Button) findViewById(R.id.btn_move_right);
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
        btnMoveLeft.setOnClickListener(this);
        btnMoveRight.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnMoveUp.setOnClickListener(this);
        btnMoveDown.setOnClickListener(this);
        btnSequence.setOnClickListener(this);
        btnSameTime.setOnClickListener(this);

        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.anim_fade_in);
        animFadeOut = AnimationUtils.loadAnimation(this, R.anim.anim_fade_out);
        animZoomIn = AnimationUtils.loadAnimation(this, R.anim.anim_zoom_in);
        animZoomOut = AnimationUtils.loadAnimation(this, R.anim.anim_zoom_out);
        animRepeat = AnimationUtils.loadAnimation(this, R.anim.anim_repeat);
        animMoveLeft = AnimationUtils.loadAnimation(this, R.anim.anim_move_left);
        animMoveRight = AnimationUtils.loadAnimation(this, R.anim.anim_move_right);
        animMoveUp = AnimationUtils.loadAnimation(this, R.anim.anim_move_up);
        animMoveDown = AnimationUtils.loadAnimation(this, R.anim.anim_move_down);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        animSequence = AnimationUtils.loadAnimation(this, R.anim.anim_sequence);
        animSameTime = AnimationUtils.loadAnimation(this, R.anim.anim_same_time);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fade_in: imageView.startAnimation(animFadeIn); break;
            case R.id.btn_fade_out: imageView.startAnimation(animFadeOut); break;
            case R.id.btn_zoom_in: imageView.startAnimation(animZoomIn); break;
            case R.id.btn_zoom_out: imageView.startAnimation(animZoomOut); break;
            case R.id.btn_repeat: imageView.startAnimation(animRepeat); break;
            case R.id.btn_move_left: imageView.startAnimation(animMoveLeft); break;
            case R.id.btn_move_right: imageView.startAnimation(animMoveRight); break;
            case R.id.btn_rotate: imageView.startAnimation(animRotate); break;
            case R.id.btn_move_up: imageView.startAnimation(animMoveUp); break;
            case R.id.btn_move_down: imageView.startAnimation(animMoveDown); break;
            case R.id.btn_sequence: imageView.startAnimation(animSequence); break;
            case R.id.btn_same_time: imageView.startAnimation(animSameTime); break;
        }

    }
}
