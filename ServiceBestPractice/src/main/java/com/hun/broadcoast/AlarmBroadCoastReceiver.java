package com.hun.broadcoast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.hun.service.LongRunningService;

/**
 * Created by LJ on 2016-04-18.
 */
public class AlarmBroadCoastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentMsg = new Intent("com.hun.messageBroadCoastReceiver");
        context.getApplicationContext().sendBroadcast(intentMsg);

        IntentFilter sendFilter = new IntentFilter();
        sendFilter.addAction("com.hun.messageBroadCoastReceiver");
        MessageBroadCoastReceiver messageBroadCoastReceiver = new MessageBroadCoastReceiver();
        context.getApplicationContext().registerReceiver(messageBroadCoastReceiver, sendFilter);
        //接收到广播后 再次启动定时器
        Intent intentService = new Intent(context, LongRunningService.class);
        context.startService(intentService);
    }
}
