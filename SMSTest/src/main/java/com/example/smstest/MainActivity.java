package com.example.smstest;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mybroadcoast.MessageReceiver;
import com.example.mybroadcoast.SendStatusReceiver;

public class MainActivity extends Activity {
    MessageReceiver messageReceiver;
    private TextView sender;
    private TextView content;
    private EditText to;
    private EditText msgInput;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sender = (TextView) findViewById(R.id.sender);
        content = (TextView) findViewById(R.id.content);

        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        // 短信广播本身就是一个有序广播,对信息接收广播进行监听
        intentFilter.setPriority(100);
        messageReceiver = new MessageReceiver(this);
        registerReceiver(messageReceiver,intentFilter);


        to = (EditText) findViewById(R.id.to);
        msgInput = (EditText) findViewById(R.id.msg_input);
        send = (Button) findViewById(R.id.send);
        // 对是否成功发送的系统广播进行监听
        IntentFilter sendFilter = new IntentFilter("SENT_SMS_ACTION");
        SendStatusReceiver sendStatusReceiver = new SendStatusReceiver(this);
        registerReceiver(sendStatusReceiver,sendFilter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*先调用SmsManager 的getDefault()方法获取到SmsManager 的实例，
                然后再调用它的sendTextMessage()方法就可以去发送短信了
                注意：发送短信也是需要声明权限的
                */
                SmsManager smsManager = SmsManager.getDefault();
                Intent sentIntent = new Intent("SENT_SMS_ACTION");
                PendingIntent pi = PendingIntent.getBroadcast
                        (MainActivity.this, 0, sentIntent, 0);
                smsManager.sendTextMessage(to.getText().toString(), null,
                        msgInput.getText().toString(), pi, null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(messageReceiver);
    }

    public TextView getContent() {
        return content;
    }

    public TextView getSender() {
        return sender;
    }

}
