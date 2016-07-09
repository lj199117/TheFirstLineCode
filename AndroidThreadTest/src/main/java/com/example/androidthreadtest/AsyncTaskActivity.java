package com.example.androidthreadtest;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/4/16 0016.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.async.DownLoadTask;

public class AsyncTaskActivity extends Activity{
    /** Called when the activity is first created. */
    private ProgressBar progressBar;

    public TextView getMsg() {
        return msg;
    }

    private TextView msg;
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如何设置窗口有刻度的效果
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.asynctask_layout);
        progressBar = (ProgressBar) this.findViewById(R.id.progressbar);
        msg = (TextView)findViewById(R.id.msg);
//        setProgressBarVisibility(true);
//        setProgressBarIndeterminate(true);
//        setProgress(3500);

        new DownLoadTask(AsyncTaskActivity.this).execute();
    }


}
