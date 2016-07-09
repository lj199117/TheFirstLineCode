package com.hnu.broadcoast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.hnu.service.GPSservice;

/**
 * Created by LJ on 2016-05-01.
 */
public class AlarmReceiver extends BroadcastReceiver {
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals(ACTION)) {
            Intent i = new Intent();
            i.setClass(context,GPSservice.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(i);
            Toast.makeText(context, "去启动服务！", Toast.LENGTH_SHORT).show();
            Log.d("AlarmReceiver", "去启动服务！");
//        }
    }
}
