package com.example.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.File;

public class MainActivity extends Activity {
    Button button = null;
    NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                // 创建Notification.Builder 构建器
                Notification.Builder notiBuilder = new Notification.Builder(MainActivity.this);
                notiBuilder.setSmallIcon(R.drawable.goldli);
                notiBuilder.setTicker("This is ticker text");
                notiBuilder.setWhen(System.currentTimeMillis());
                notiBuilder.setContentText("This is content text");
                notiBuilder.setContentTitle("This is content title");
                // 构建PendingIntent 可理解为延迟执行Intent
                Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                notiBuilder.setContentIntent(pendingIntent);
                // 控制通知的声音
                Uri uri = Uri.fromFile(new File("/system/media/audio/notifications/Beat_Box_Android.ogg"));
                notiBuilder.setSound(uri);
                // 控制通知的震动 (需要声明权限)
                long[] vibrates = {0, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000};
                notiBuilder.setVibrate(vibrates);
                // 控制通知的LED灯光
                notiBuilder.setLights(Color.RED,1000,1000);
                // 构建Notification 对象
                Notification notification = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    notification = notiBuilder.build();
                    /*
                    // 控制通知的LED灯光
                    notification.ledARGB = Color.RED;
                    notification.ledOnMS = 1000;
                    notification.ledOffMS = 1000;
                    notification.flags = Notification.FLAG_SHOW_LIGHTS;*/
                }
                notificationManager.notify(1, notification);
                /*
                //已经失效
                Notification notification = new Notification(R.drawable.banana_pic, "This is ticker text",
                        System.currentTimeMillis());
                notification.setLatestEventInfo(context, "This is content title", "This is content text", null);*/
            }
        });
    }


}
