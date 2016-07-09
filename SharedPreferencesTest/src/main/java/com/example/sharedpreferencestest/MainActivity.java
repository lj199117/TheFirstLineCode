package com.example.sharedpreferencestest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button = null;
    Button getButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SharedPreferences sharedPreferences =
                        getSharedPreferencesMethod1(MainActivity.this,
                                "MainSharedPreferences",
                                MODE_PRIVATE);*/
                SharedPreferences sharedPreferences =
                        getSharedPreferencesMethod2(MainActivity.this,
                                MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("age",12);
                editor.putString("name", "goldliclass");
                editor.commit();
            }
        });

        getButton = (Button)findViewById(R.id.getButton);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 注意一点，你用的什么方法存，那就要用同样的方法取
                 */
                SharedPreferences sharedPreferences =
                        getSharedPreferencesMethod2(MainActivity.this,MODE_PRIVATE);
                int age = sharedPreferences.getInt("age", 0);
                String name = sharedPreferences.getString("name","");
                Log.i("MainActivity", "" + age);
                Log.i("MainActivity", name);
            }
        });
    }

    public SharedPreferences getSharedPreferencesMethod1(Context context,String name,int model){
        /**
         * 第一个参数用于指定SharedPreferences 文件的名称
         * 第二个参数用于指定操作模式，主要有两种模式可以选择，MODE_PRIVATE 和MODE_MULTI_PROCESS
         */
        return context.getSharedPreferences(name,model);
    }

    public SharedPreferences getSharedPreferencesMethod2(Activity activity,int model){
        /**
         * 使用这个方法时会自动将当前活动的类名作为SharedPreferences 的文件名
         */
        return activity.getPreferences(model);
    }

    public SharedPreferences getSharedPreferencesMethod3(Context context){
        /**
         * 自动使用当前应用程序的包名作为前缀来命名SharedPreferences 文件
         */
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
