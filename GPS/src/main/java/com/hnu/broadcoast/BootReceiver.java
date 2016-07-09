package com.hnu.broadcoast;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.hnu.MainActivity;
import com.hnu.OtherActivity;

/**
 * Created by LJ on 2016-04-29.
 */
public class BootReceiver extends BroadcastReceiver {
    static int i =0;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
        if(msg.what==1){
            Log.d("BootReceiver","开机了！"+i++);
            Toast.makeText((Context)(msg.obj), "开机了！", Toast.LENGTH_SHORT).show();
        }
        return true;
        }
    });
    @Override
    public void onReceive(final Context context, Intent intentParam) {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(true){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Message message = new Message();
//                    message.obj = context;
//                    message.what=1;
//                    handler.sendMessage(message);
//                }
//            }
//        }).start();
        Intent intent = new Intent();
        intent.setData(Uri.parse("hnuandroidsdk://"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
            Toast.makeText(context, "开机了！", Toast.LENGTH_SHORT).show();
            Log.d("BootReceiver","开机了！");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "没有该子APP，请下载安装", Toast.LENGTH_SHORT).show();
            Log.d("BootReceiver", "没有该子APP，请下载安装！");
        }
    }
}
