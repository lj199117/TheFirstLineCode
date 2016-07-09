package com.hnu.custombrodcast_standard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent("com.hnu.custombrodcast_standard.MY_BROADCAST");
            //通过Context的  sendBroadcast发送"标准广播"
            sendBroadcast(intent);
            }
        });

        Button anoButton = (Button)findViewById(R.id.anotherActivity);
        anoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main2Activity.startAction(MainActivity.this);
            }
        });
    }


}
