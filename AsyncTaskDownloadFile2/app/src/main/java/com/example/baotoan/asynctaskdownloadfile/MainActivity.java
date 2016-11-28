package com.example.baotoan.asynctaskdownloadfile;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtLink;
    private Button btnDownload;
    private ProgressDialog prgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLink = (EditText) findViewById(R.id.edt_link);
        btnDownload = (Button) findViewById(R.id.btn_download);
        btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnDownload) {
            String url = edtLink.getText().toString().trim();
            if(url.length() > 0) {
                new DownloadTask().execute(url, "demo.jpg");
            } else {
                Toast.makeText(this, "Please enter a link to download", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        prgDialog = new ProgressDialog(this);
        prgDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        prgDialog.setTitle("Downloading");
        prgDialog.setMessage("Please wait....");
        prgDialog.setMax(100);
        prgDialog.setCancelable(false);
        prgDialog.show();
        return prgDialog;
    }

    class DownloadTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(1);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                BufferedInputStream bIs = new BufferedInputStream(url.openStream(), 10 * 1024);
                BufferedOutputStream bOs = new BufferedOutputStream(new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/" + params[1]));

                int lengthOfFile = connection.getContentLength();

                byte[] data = new byte[1024];
                int totalByteReaded = 0;
                int byteReaded = 0;
                while((byteReaded = bIs.read(data)) != -1) {
                    // Write to destination
                    bOs.write(data, 0, byteReaded);
                    // update progress
                    totalByteReaded += byteReaded;
                    publishProgress(totalByteReaded * 100 / lengthOfFile);
                }
                bOs.flush();
                bOs.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            prgDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            prgDialog.dismiss();
        }
    }
}
