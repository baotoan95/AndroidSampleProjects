package com.example.baotoan.checkboxandradiobutton;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private RadioButton radFemale;
    private RadioButton radMale;
    private CheckBox cbShopping;
    private CheckBox cbFootball;
    private CheckBox cbTravel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radFemale = (RadioButton) findViewById(R.id.rad_female);
        radFemale.setOnCheckedChangeListener(this);
        radMale = (RadioButton) findViewById(R.id.rad_male);
        radMale.setOnCheckedChangeListener(this);

        cbShopping = (CheckBox) findViewById(R.id.cb_shopping);
        cbShopping.setOnClickListener(this);
        cbFootball = (CheckBox) findViewById(R.id.cb_football);
        cbFootball.setOnClickListener(this);
        cbTravel = (CheckBox) findViewById(R.id.cb_travel);
        cbTravel.setOnClickListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView == radFemale && isChecked) {
            Toast.makeText(this, "You're a woman", Toast.LENGTH_SHORT).show();
        } else if(buttonView == radMale && isChecked) {
            Toast.makeText(this, "You're a man", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int btn = v.getId();

        if(btn == R.id.cb_shopping && cbShopping.isChecked()) {
            Toast.makeText(this, "You like shopping", Toast.LENGTH_SHORT).show();
        } else if(btn == R.id.cb_football && cbFootball.isChecked()) {
            Toast.makeText(this, "You like playing football", Toast.LENGTH_SHORT).show();
        } else if(btn == R.id.cb_travel && cbTravel.isChecked()) {
            Toast.makeText(this, "You like travel", Toast.LENGTH_SHORT).show();
        }
    }
}
