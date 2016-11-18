package com.example.baotoan.networkingdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnGetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetData = (Button) findViewById(R.id.btn_get_data);
        btnGetData.setOnClickListener(this);
    }

    Handler netWorkHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String data = msg.obj.toString();

            Serializer serializer = new Persister();
            try {
                CatalogModel catalogModel = serializer.read(CatalogModel.class, data);
                Toast.makeText(MainActivity.this, catalogModel.getCds().get(0).getTitle(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onClick(View v) {
        if(v == btnGetData) {
            new Thread() {
                @Override
                public void run() {
                    String data = getDataFormUrl("http://www.w3schools.com/xml/cd_catalog.xml");
                    Message message = new Message();
                    message.obj = data;
                    netWorkHandler.sendMessage(message);
                }
            }.start();
        }
    }

    private String getDataFormUrl(String url) {
        String result = null;
        int CONNECT_TIMEOUT = 3000;
        int SOCKET_TIMEOUT = 5000;
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SOCKET_TIMEOUT);

        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if(httpResponse != null) {
                InputStream inputStream = httpResponse.getEntity().getContent();
                result = convertStreamToString(inputStream);
            }
        } catch (ConnectTimeoutException timeEx) {
            result = "Connection timeout";
        } catch(SocketTimeoutException socEx) {
            result = "Socket timeout";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String convertStreamToString(InputStream inputStream) {
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
