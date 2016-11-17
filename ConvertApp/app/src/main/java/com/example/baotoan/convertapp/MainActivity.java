package com.example.baotoan.convertapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout layoutDistance, layoutWeight, layoutTemperature, layoutSpeed;
    public static int DISTANCE_CONVERT = 0;
    public static int WEIGHT_CONVERT = 1;
    public static int TEMPERATURE_CONVERT = 2;
    public static int SPEED_CONVERT = 3;

    private int convertType = DISTANCE_CONVERT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutDistance = (RelativeLayout) findViewById(R.id.layout_distance);
        layoutWeight = (RelativeLayout) findViewById(R.id.layout_weight);
        layoutTemperature = (RelativeLayout) findViewById(R.id.layout_temperature);
        layoutSpeed = (RelativeLayout) findViewById(R.id.layout_speed);

        layoutDistance.setOnClickListener(this);
        layoutWeight.setOnClickListener(this);
        layoutTemperature.setOnClickListener(this);
        layoutSpeed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == layoutDistance) {
            setConvertType(DISTANCE_CONVERT);
        } else if(v == layoutWeight) {
            setConvertType(WEIGHT_CONVERT);
        } else if(v == layoutTemperature) {
            setConvertType(TEMPERATURE_CONVERT);
        } else {
            setConvertType(SPEED_CONVERT);
        }
    }

    private void setConvertType(int convertType) {
        this.convertType = convertType;
        Intent intent = new Intent(this, ConvertActivity.class);
        intent.putExtra("CONVERT_TYPE", this.convertType);
        startActivity(intent);
    }
}
