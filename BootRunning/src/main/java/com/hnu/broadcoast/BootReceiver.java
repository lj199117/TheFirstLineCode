package com.hnu.broadcoast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hnu.service.GPSService;

/**
 * Created by LJ on 2016-05-01.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent();
        i.setClass(context, GPSService.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(i);

        //启动应用，参数为需要自动启动的应用的包名
//        Intent i = getPackageManager().getLaunchIntentForPackage(packageName);
//        context.startActivity(i);
    }
}
