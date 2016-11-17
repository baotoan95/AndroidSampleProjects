package com.example.baotoan.floatbuttondemo;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton btnLogin;
    private FloatingActionButton btnFacebook;
    private FloatingActionButton btnTwitter;
    private FloatingActionButton btnGoogle;

    private boolean isShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (FloatingActionButton) findViewById(R.id.btn_login);
        btnFacebook = (FloatingActionButton) findViewById(R.id.fab_facebook);
        btnTwitter = (FloatingActionButton) findViewById(R.id.fab_twitter);
        btnGoogle = (FloatingActionButton) findViewById(R.id.fab_google);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin) {
            toggle();
        }
    }

    private void toggle() {
        if(isShow) {
            btnFacebook.show();
            btnTwitter.show();
            btnGoogle.show();
            isShow = false;
        } else {
            btnFacebook.hide();
            btnTwitter.hide();
            btnGoogle.hide();
            isShow = true;
        }
    }
}
