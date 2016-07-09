package com.example.androidthreadtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    private TextView text;
    private Button changeText;
    private TextView textCount;
    private Button changeTextCount;
    private Button asyncTask;
    public static final int CHANGE_TEXT = 0;
    public static int FLAG_OPEN = 1;
    public static int TIME = 1;
    Handler handler;
    Handler handlerCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        changeText = (Button) findViewById(R.id.change_text);
        textCount = (TextView) findViewById(R.id.textCount);
        changeTextCount = (Button) findViewById(R.id.change_textCount);
        asyncTask = (Button)findViewById(R.id.asyncTask);
        asyncTask.setOnClickListener(this);
        changeText.setOnClickListener(this);
        changeTextCount.setOnClickListener(this);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what == CHANGE_TEXT && FLAG_OPEN %2 ==0){
                    text.setText("Nice to meet you");
                }else if(msg.what == CHANGE_TEXT && FLAG_OPEN %2 !=0){
                    text.setText("Hello world");
                }
                return true;
            }
        });

        handlerCount = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.arg1 == 0){
                    textCount.setText(""+msg.arg2);
                }
                return true;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = CHANGE_TEXT;
                        FLAG_OPEN++;
                        Log.d("MainActivity",""+FLAG_OPEN);
                        handler.sendMessage(msg);
                    }
                }).start();
                break;
            case R.id.change_textCount:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            try {
                                Thread.sleep(1000);
                                Message message = new Message();
                                message.arg2 = TIME++;
                                message.arg1 = 0;
                                Log.d("MainActivity","TIME:"+TIME);
                                handlerCount.sendMessage(message);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                break;
            case R.id.asyncTask:
                Intent intent = new Intent(MainActivity.this,AsyncTaskActivity.class);
                startActivity(intent);
                break;
        }
    }
}
