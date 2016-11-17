package com.example.baotoan.notificationsample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnNotify;
    private NotificationCompat.Builder builder;
    private EditText edtContent;

    private int countNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mapping components
        btnNotify = (Button) findViewById(R.id.btn_notify);
        builder = new NotificationCompat.Builder(this);
        edtContent = (EditText) findViewById(R.id.edt_content);

        // Setting components
        btnNotify.setOnClickListener(this);

        builder.setSmallIcon(R.drawable.small_icon);
        builder.setContentTitle("New notification");
        builder.setContentText("You have some events");
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.flag));
        builder.setOngoing(true); // Not allow user clear notify

        android.support.v4.app.NotificationCompat.InboxStyle inboxStyle = new android.support.v4.app.NotificationCompat.InboxStyle(builder);
    }

    @Override
    public void onClick(View v) {
        if(v == btnNotify) {
            Intent intent = new Intent(this, SecondActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[] {intent}, PendingIntent.FLAG_UPDATE_CURRENT);

            // count notify
            builder.setNumber(++countNotify);
            // Put inbox content line
            ((android.support.v4.app.NotificationCompat.InboxStyle)builder.mStyle).addLine(edtContent.getText().toString());

            builder.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(100, builder.build());
        }
    }
}
