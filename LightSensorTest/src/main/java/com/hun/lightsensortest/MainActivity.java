package com.hun.lightsensortest;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
    private SensorManager sensorManager;
    private TextView lightLevel;
    private SensorEventListener listener = new SensorEventListener(){
        @Override
        public void onSensorChanged(SensorEvent event) {
            // values数组中第一个下标的值就是当前的光照强度
            float value = event.values[0];
            lightLevel.setText("Current light level is " + value + " lx");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightLevel = (TextView)findViewById(R.id.light_level);
        /**
         * 1.通过Context获取到传感器实例SensorManager
         2.通过getDefaultSensor(arg传感器类型) 获取传感器
         3.借助SensorEventListener对传感器进行监听
         4.通过SensorManager 的registerListener()方法来注册SensorEventListener才能使其生效(绑定sensor与监听器)
         5.程序退出或传感器使用完毕时，一定要调用unregisterListener ()方法将使用的资源释放掉
         意外的是 居然不要 声明权限
         */
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sensorManager != null)
            sensorManager.unregisterListener(listener);
    }
}
