package com.example.mybroadcoast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/4/15 0015.
 */
public class SendStatusReceiver extends BroadcastReceiver {
    Context context;
    public SendStatusReceiver(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (getResultCode() == Activity.RESULT_OK) {
// 短信发送成功
            Toast.makeText(context, "Send succeeded",
                    Toast.LENGTH_LONG).show();
        } else {
// 短信发送失败
            Toast.makeText(context, "Send failed",
                    Toast.LENGTH_LONG).show();
        }
    }
}
