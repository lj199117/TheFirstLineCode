package com.hnu.localbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button button= null;
    LocalBroadcastManager localBroadcastManager = null;
    IntentFilter intentFilter = null;
    LocalReceiver localReceiver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);// 获取实例

        //注册广播  (这是以代码的形式注册广播,不知道能否用AndroidManifest.xml中注册)
        intentFilter = new IntentFilter("com.hnu.localbroadcast.MY_LOCALBROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送广播
                Intent intent = new Intent("com.hnu.localbroadcast.MY_LOCALBROADCAST");
                localBroadcastManager.sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }


}
