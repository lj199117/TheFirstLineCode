package com.hnu.custombrodcast_standard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by LJ on 2016/4/11.
 */
public class AnotherBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MainActivity", "应用程序内发出的广播，其他的应用程序应该也是可以收到的\r\nreceived in AnotherBroadcastReceiver");
        Toast.makeText(context, "应用程序内发出的广播，其他的应用程序应该也是可以收到的\r\nreceived in AnotherBroadcastReceiver",
                Toast.LENGTH_SHORT).show();
        //abortBroadcast()会导致标准的能继续接收而有序的则不能再接收
        abortBroadcast();
    }
}
