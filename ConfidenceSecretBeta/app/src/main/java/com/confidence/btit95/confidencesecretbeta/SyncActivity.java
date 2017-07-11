package com.confidence.btit95.confidencesecretbeta;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SyncActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SyncActivity.this, "Synchronized", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}
