package com.hun.broadcoast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by LJ on 2016-04-18.
 */
public class MessageBroadCoastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"MessageBroadCoastReceiver",Toast.LENGTH_SHORT).show();
    }
}
