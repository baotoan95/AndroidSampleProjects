package com.example.baotoan.wikicountry;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by BaoToan on 10/31/2016.
 */

public class Utils {
    public static String getDataFormUrl(String url) {
        String result = "";
        final int CONNECTION_TIMEOUT = 3000;
        final int SOCKET_TIMEOUT = 5000;

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SOCKET_TIMEOUT);

        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (null != httpResponse) {
                InputStream inputStream = httpResponse.getEntity().getContent();
                result = convertStreamToString(inputStream);
            }
        } catch (ConnectTimeoutException conEx) {
            result = "Connection timeout";
        } catch (SocketTimeoutException socEx) {
            result = "Socket timeout";
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        return result;
    }

    public static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static Drawable loadImageFromWebOperations(String url) {
        try {
            InputStream inputStream = (InputStream) new URL(url).getContent();
            Drawable drawable = Drawable.createFromStream(inputStream, "src name");
            return drawable;
        } catch (IOException e) {
            return null;
        }
    }

    public static void setBitmapToImage(final String url, final ImageView imageView) {
        final Handler networkHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Drawable drawable = (Drawable) msg.obj;
                if (null != drawable) {
                    imageView.setImageDrawable(drawable);
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                Drawable drawable = Utils.loadImageFromWebOperations(url);
                Message message = new Message();
                message.obj = drawable;
                networkHandler.sendMessage(message);
            }
        }).start();
    }
}
