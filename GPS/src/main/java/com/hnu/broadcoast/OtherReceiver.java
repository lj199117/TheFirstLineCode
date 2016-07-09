package com.hnu.broadcoast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.hnu.OtherActivity;

/**
 * Created by LJ on 2016-04-29.
 */
public class OtherReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intentParam) {

        Intent intent = new Intent(context,OtherActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
            Log.d("OtherReceiver","OtherReceiver!");
        } catch (Exception e) {
            Log.d("OtherReceiver", "OtherReceiver!");
        }
    }
}
