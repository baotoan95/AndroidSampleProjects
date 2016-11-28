package com.example.baotoan.convertapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConvertActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText editInput;
    private Spinner spnUnit;
    private TextView txtResult;

    private String[] units;
    private int currentUnit = MainActivity.DISTANCE_CONVERT;
    private int convertType;

    private float[][] convertDistanceArray = {
          // meter | mile | cm | inch | yd
            {1, 0.000621371f, 100f, 39.3701f, 1.09361f,}, // meter
            {1609.339f, 1, 160933.9f, 63359.8f, 1759.99f},// mile
            {0.01f, 9.999969f, 1, 0.39369f, 0.01093f},    // cm
            {0.025399f, 1.5782f, 2.53999f, 1, 0.02777f},  // inch
            {0.914f, 0.000568f, 91.4397f, 35.999f, 1}     // yd
    };

    private float[][] convertWeightArray = {

    };

    private float[][] convertTemperatureArray = {};

    private float[][] convertSpeedArray = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        // Mapping activity
        editInput = (EditText) findViewById(R.id.edt_input);
        spnUnit = (Spinner) findViewById(R.id.spinner);
        txtResult = (TextView) findViewById(R.id.txt_result);

        // get convert type
        convertType = getIntent().getExtras().getInt("CONVERT_TYPE");

        // get values for units
        units = getResources().getStringArray(R.array.unit);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.unit, R.layout.support_simple_spinner_dropdown_item);
        spnUnit.setAdapter(arrayAdapter);

        editInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(ConvertActivity.this, "Changed", Toast.LENGTH_SHORT).show();
                convert(currentUnit, getConvertArray());
            }
        });

        spnUnit.setOnItemSelectedListener(this);
    }

    protected void convert(int currentUnit, float[][] convertArray) {
        // check input
        if(editInput.getText().toString().length() <= 0) {
            txtResult.setText("");
            return;
        }

        // convert
        float input = Float.parseFloat(editInput.getText().toString());
        String result = "";

        for(int i = 0; i < convertArray.length; i++) {
            if(currentUnit != i) {
                result += units[i];
                result += ": ";
                result += (input * convertArray[currentUnit][i]);
                result += "\n";
            }
        }

        txtResult.setText(result);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currentUnit = position;
        convert(currentUnit, getConvertArray());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private float[][] getConvertArray() {
        switch (convertType) {
            case 0: return convertDistanceArray; // distance
            case 1: return convertWeightArray; // weight
            case 2: return convertTemperatureArray;
            case 3: return convertSpeedArray;
        }
        return null;
    }
}
