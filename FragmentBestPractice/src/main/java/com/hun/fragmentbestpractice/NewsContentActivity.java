package com.hun.fragmentbestpractice;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.hun.fragment.NewsContentFragment;

/**
 * Created by LJ on 2016/4/11.
 */
public class NewsContentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_content_layout);

        Intent intent = getIntent();
        String newsTitle = intent.getStringExtra("news_title");
        String newsContent = intent.getStringExtra("news_content");

        //更新frag的内容
        NewsContentFragment newsContentFragment = (NewsContentFragment)getFragmentManager()
                .findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle,newsContent);

    }

    public static void startAction(Context context, String newsTitle,String newsContent){
        Intent intent = new Intent(context,NewsContentActivity.class);

        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent); //通过别人来启动自己,顺便带上必要的参数
    }
}
