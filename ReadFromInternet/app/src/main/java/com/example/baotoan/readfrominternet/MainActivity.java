package com.example.baotoan.readfrominternet;

import android.os.AsyncTask;
<<<<<<< HEAD
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
>>>>>>> cd54f619a4c3a0e5beff79fec1069729482661ab
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

<<<<<<< HEAD
=======
import javax.net.ssl.HttpsURLConnection;

>>>>>>> cd54f619a4c3a0e5beff79fec1069729482661ab
public class MainActivity extends AppCompatActivity {
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.txtView);

        new ReadFromInternet().execute("http://btit95.esy.es/");
    }

    private class ReadFromInternet extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
<<<<<<< HEAD
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String content = "";
                String line;
                while((line = bufferedInputStream.readLine()) != null) {
                    content += line;
=======
                URL url = new URL("");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedInputStream bufferedInputStream = new BufferedInputStream(conn.getInputStream());
                String content = "";
                int readed = 0;
                while((readed = bufferedInputStream.read()) != -1) {
                    content += (char) readed;
>>>>>>> cd54f619a4c3a0e5beff79fec1069729482661ab
                }
                return content;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtView.setText(s);
        }
    }
}
