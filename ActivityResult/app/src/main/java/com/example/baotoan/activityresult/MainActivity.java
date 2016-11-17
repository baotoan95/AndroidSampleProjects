package com.example.baotoan.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static int LOGIN_REQUEST_CODE = 1;
    private TextView lblTitle;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblTitle = (TextView) findViewById(R.id.lbl_title);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, LOGIN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LOGIN_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                lblTitle.setText("Login successful!\n Hi, " + bundle.getString("USERNAME"));
                btnLogin.setVisibility(View.INVISIBLE);
            } else {
                lblTitle.setText("Login fail!\nPlease login one more time");
            }
        }
    }
}
