package com.example.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;

import com.example.myservice.MyIntentService;
import com.example.myservice.MyService;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private Button startIntentService;

    private MyService.DownloadBinder downloadBinder;
    /*onServiceConnected()方法和onServiceDisconnected()方法，这两个方法分别会在活动与服务
    成功绑定以及解除绑定的时候调用*/
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            /*我们可以在活动中根据具体的场景来调用DownloadBinder 中的任何public 方法，
            即实现了指挥服务干什么，服务就去干什么的功能*/
            downloadBinder = (MyService.DownloadBinder)service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        startIntentService = (Button) findViewById(R.id.start_intent_service);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent); // 启动服务
                Log.d("MainActivity", "startService executed");
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent); // 停止服务
                Log.d("MainActivity", "stopService executed");
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE); // 绑定服务
                Log.d("MainActivity", "bindService executed");
                break;
            case R.id.unbind_service:
                unbindService(serviceConnection); // 解绑服务
                Log.d("MainActivity", "unbindService executed");
                break;
            case R.id.start_intent_service:
                Intent intent = new Intent(this, MyIntentService.class);
                startService(intent);
                Log.d("MainActivity", "start IntentsService executed");
                break;
            default:
                break;
        }
    }
}
