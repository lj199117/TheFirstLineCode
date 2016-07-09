package com.example.async;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidthreadtest.AsyncTaskActivity;

/**
 * Created by Administrator on 2016/4/16 0016.
 */
public class DownLoadTask extends AsyncTask<Void, Integer, Boolean> {
    public static int loadCount = 0;
    AsyncTaskActivity context;
    ProgressBar progressDialog;
    TextView msg;
    public DownLoadTask(Context context) {
        super();
        this.context = (AsyncTaskActivity)context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = context.getProgressBar();
        progressDialog.setMax(100);
        msg = context.getMsg();
        progressDialog.setVisibility(View.VISIBLE); // 显示进度对话框
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            while (true) {
                int downloadPercent = doDownload(); // 这是一个虚构的方法
                publishProgress(downloadPercent);
                if (downloadPercent >= 100) {
                    break;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
// 在这里更新下载进度
        //progressDialog.setMessage("Downloaded " + values[0] + "%");
        msg.setText("Downloaded " + values[0] + "%");
        progressDialog.setProgress((int) (values[0]));
        progressDialog.setSecondaryProgress((int) (values[0])+5);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        progressDialog.setVisibility(View.GONE); // 关闭进度对话框
// 在这里提示下载结果
        if (result) {
            Toast.makeText(context, "Download succeeded",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, " Download failed",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private int doDownload() {
        try {
            Thread.sleep(100);
            loadCount++;
            Log.d("MainActivity", "loadCount:" + loadCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return loadCount;
    }

}