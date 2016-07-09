package com.example.myservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by LJ on 2016-04-17.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService"); // 调用父类的有参构造函数
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while(true){
            try {
                Thread.sleep(1000);
                // 打印当前线程的id
                Log.d("MyIntentService", "Thread id is " + Thread.currentThread().
                        getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy executed");
    }
}
