package com.hnu.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hnu.R;
import com.hnu.broadcoast.AlarmReceiver;
import com.hnu.pojo.ResponseObject;
import com.hnu.pojo.User;
import com.hnu.util.EmailUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.Date;

/**
 * Created by LJ on 2016-05-01.
 */
public class GPSservice extends Service
        implements AMapLocationListener {

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void startGPS(){
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置定位监听
        locationClient.setLocationListener(this);
        initOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
        Log.d("GPSservice", "startGPS()方法启动");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("GPSservice", "执行于 " + new Date().toString());
                startGPS();
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 60 * 1000;//60 * 60 * 1000; // 这是一小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }
    // 根据控件的选择，重新设置定位参数
    private void initOption() {
        // 设置是否需要显示地址信息
        locationOption.setNeedAddress(true);
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
        locationOption.setGpsFirst(true);
        String strInterval = "5000";
        if (!TextUtils.isEmpty(strInterval)) {
            // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
            locationOption.setInterval(Long.valueOf(strInterval));
        }
    }

    // 定位监听
    @Override
    public void onLocationChanged(AMapLocation loc) {
        if (null != loc) {
            if (loc.getErrorCode() == 0) {
//                sendToDB(loc);
                sendToEmail(loc);
            }
        }else{
            //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
            Log.e("GPSservice", "location Error, ErrCode:"
                    + loc.getErrorCode() + ", errInfo:"
                    + loc.getErrorInfo());
        }
    }

    public void sendToDB(final AMapLocation loc){
        RequestParams requestParams = new RequestParams();
        requestParams.addBodyParameter("username", loc.getAddress());
        requestParams.addBodyParameter("password", loc.getCityCode());
        requestParams.addBodyParameter("flag", "register");
        Log.d("GPSservice", loc.getCityCode() + loc.getAddress());
//        new HttpUtils().send(HttpRequest.HttpMethod.POST,
//                "http://192.168.1.105:8080/ls_server/servlet/UserServlet",
//                requestParams, new RequestCallBack<String>() {
//                    @Override
//                    public void onSuccess(ResponseInfo<String> responseInfo) {
//                        Log.d("GPSservice", "onSuccess()方法");
//                        ResponseObject<User> responseObject = new GsonBuilder().create().fromJson(responseInfo.result, new TypeToken<ResponseObject<User>>() {
//                        }.getType());
//                        if (responseObject.getState() == 1) {
//                            Log.d("GPSservice", loc.getCity() + loc.getAddress());
//                            Toast.makeText(GPSservice.this, "存储数据库成功", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(GPSservice.this, "数据重复，存储数据库失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(HttpException e, String s) {
//                        Log.d("GPSservice", s);
//                        Toast.makeText(GPSservice.this, "网络异常"+s, Toast.LENGTH_SHORT).show();
//                    }
//                });
        this.sendToEmail(loc);
    }

    public void sendToEmail(AMapLocation loc){
//        EmailUtil.sendMail("451615539@qq.com","location monitor",
//                "报告，李锦现在的位置是："+loc.getAddress()+
//                        ",纬度"+loc.getLatitude()+
//                        "°,经度"+loc.getLongitude()+
//                        "°,城市代码为"+loc.getCityCode());
        EmailUtil.sendMail("goldliclass@163.com","location monitor",
                "报告，李锦现在的位置是："+loc.getAddress()+
                        ",纬度"+loc.getLatitude()+
                        "°,经度"+loc.getLongitude()+
                        "°,城市代码为"+loc.getCityCode());
    }
}
