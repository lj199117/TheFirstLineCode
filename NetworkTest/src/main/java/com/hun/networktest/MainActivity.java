package com.hun.networktest;

import android.app.Activity;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button sendRequest;
    private Button sendHttpClient;
    private TextView responseText;
    public static final int SHOW_RESPONSE = 0;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == SHOW_RESPONSE){
                responseText.setText((String) (msg.obj));
//                Log.d("MainActivity",(String)(msg.obj));
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequest = (Button)findViewById(R.id.send_request);
        sendHttpClient = (Button)findViewById(R.id.send_http);
        responseText = (TextView)findViewById(R.id.response);
        sendRequest.setOnClickListener(this);
        sendHttpClient.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.send_request:
                sendRequestWithHttpURLConnection();
                break;
            case R.id.send_http:
                sendRequestWithHttpClient();
                break;
        }
    }


    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                BufferedReader br = null;
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL("http://www.baidu.com");
                    httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    is = httpURLConnection.getInputStream();
                    //读取内容
                    br = new BufferedReader(new InputStreamReader(is));
                    StringBuffer sb = new StringBuffer(1000);
                    String str = null;
                    while ((str = br.readLine()) != null) {
                        sb.append(str);
                    }
                    //这里因为是子线程，不能操作UI 直接setText()
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    message.obj = sb.toString();
                    handler.sendMessage(message);
                    Log.d("MainActivity", sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(is!=null)
                            is.close();
                        if(br!=null)
                            br.close();
                        if(httpURLConnection!=null)
                            httpURLConnection.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();
    }

    private void sendRequestWithHttpClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://www.baidu.com");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
// 请求和响应都成功了
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity,"utf-8");
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
// 将服务器返回的结果存放到Message中
                        message.obj = response.toString();
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
