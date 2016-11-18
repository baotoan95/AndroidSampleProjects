package com.example.baotoan.fragmentdemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.baotoan.fragmentdemo.R;
import com.example.baotoan.fragmentdemo.fragments.FragmentDetail;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();

        FragmentDetail fragmentDetail = (FragmentDetail) getSupportFragmentManager().findFragmentById(R.id.frg_detail);
        fragmentDetail.showInformation(bundle.getString("INFO"));

    }
}
