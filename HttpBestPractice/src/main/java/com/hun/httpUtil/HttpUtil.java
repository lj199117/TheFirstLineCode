package com.hun.httpUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * endHttpRequest()方法会在服务器还来得及响应的时候就执行结束了，
 * 当然也就无法返回响应的数据了。那么遇到这种情况应该怎么办呢？
 * 其实解决方法并不难，只需要使用Java 的回调机制(接口)就可以了，
 * 下面就让我们来学习一下回调机制到底是如何使用的
 */
public class HttpUtil {
    /**
     * onFinish()方法表示当服务器成功响应我们请求的时候调用，
     * onError()表示当进行网络操作出现错误的时候调用
     */
    public interface HttpCallbackListener {
        void onFinish(String response);
        void onError(Exception e);
    }

    public static void sendHttpRequest(final String address,
                                       final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String str = null;
                    StringBuffer sb = new StringBuffer(1000);
                    while((str = br.readLine()) != null){
                        sb.append(str);
                    }
                    if (listener != null) {
                        // 回调onFinish()方法,子线程不能处理UI 所以用回调
                        listener.onFinish(sb.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        // 回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if(connection != null)
                        connection.disconnect();
                }
            }
        }).start();
    }
}
