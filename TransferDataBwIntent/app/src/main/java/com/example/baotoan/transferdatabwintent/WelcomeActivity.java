package com.example.baotoan.transferdatabwintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtWelcome;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtWelcome = (TextView) findViewById(R.id.txt_welcome);
        btnBack = (Button) findViewById(R.id.btn_black);

        Bundle bundle = getIntent().getExtras();
        txtWelcome.setText("Hi, " + bundle.getString("NAME"));

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
