package com.example.baotoan.sharepreferencedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
    private ToggleButton togSound;
    private SeekBar seekVolume;
    private TextView lblValue;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        togSound = (ToggleButton) findViewById(R.id.tog_sound);
        seekVolume = (SeekBar) findViewById(R.id.seek_volume);
        lblValue = (TextView) findViewById(R.id.lbl_value);

        togSound.setOnCheckedChangeListener(this);
        seekVolume.setOnSeekBarChangeListener(this);

        sharedPreferences = getSharedPreferences("mySettings", Context.MODE_PRIVATE);

        boolean enableSound = sharedPreferences.getBoolean("SOUND", false);
        int volumeValue = sharedPreferences.getInt("VOLUME", 15);

        togSound.setChecked(enableSound);
        seekVolume.setProgress(volumeValue);
        lblValue.setText(String.valueOf(volumeValue));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("SOUND", isChecked);
        editor.commit();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        lblValue.setText(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("VOLUME", seekBar.getProgress());
        editor.commit();
    }
}
