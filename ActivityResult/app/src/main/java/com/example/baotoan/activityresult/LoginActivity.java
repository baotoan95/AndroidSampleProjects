package com.example.baotoan.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editUsername;
    private EditText editPassword;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = (EditText) findViewById(R.id.edit_username);
        editPassword = (EditText) findViewById(R.id.edit_password);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(editUsername.getText().toString().equals("baotoan") && editPassword.getText().toString().equals("123")) {
            intent.putExtra("USERNAME", editUsername.getText().toString());
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }
}
