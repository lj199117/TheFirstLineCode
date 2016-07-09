package com.hnu.custombrodcast_standard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent("com.hnu.custombrodcast_standard.MY_BROADCAST");
            //通过Context的  sendOrderedBroadcast发送"有序广播"
            sendOrderedBroadcast(intent,null);
            }
        });
    }

    public static void startAction(Context context){
        Intent intent = new Intent(context,Main2Activity.class);
        context.startActivity(intent);
    }
}
