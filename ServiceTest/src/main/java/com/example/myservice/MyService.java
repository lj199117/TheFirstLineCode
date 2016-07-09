package com.example.myservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.servicetest.MainActivity;
import com.example.servicetest.R;


/**
 * Created by Administrator on 2016/4/16 0016.
 */
public class MyService extends Service {

    public DownloadBinder mBinder = new DownloadBinder();
    public class DownloadBinder extends Binder {
        public void startDownload() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // start downloading
                }
            }).start();
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //设定为前台服务(其实就是设置通知，不过不再由NotificationManager进行管理)
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker("Notification coming to see");
        builder.setContentText("Notification comes");
        builder.setContentTitle("Notification title");
        builder.setSmallIcon(R.drawable.ic_launcher);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(this, MainActivity.class),PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }
        //通过这个方法显示前台服务（其实就是通知）
        startForeground(1,notification);
        Log.d("MyService", "onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand executed");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // do something here
                while(true){
                    try {
                        Thread.sleep(1000);
                        // 打印当前线程的id
                        Log.d("MyService", "Thread id is " + Thread.currentThread().
                                getId());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy executed");
    }


}
