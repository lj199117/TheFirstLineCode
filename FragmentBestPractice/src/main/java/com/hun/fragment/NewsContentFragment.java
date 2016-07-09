package com.hun.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hun.fragmentbestpractice.R;
import com.hun.pojo.News;

/**
 * Created by LJ on 2016/4/11.
 */
public class NewsContentFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.news_content_frag_layout,container,false);

        return view;

    }



    public void refresh(String title,String content){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView news_title = (TextView)view.findViewById(R.id.news_title);
        TextView news_content = (TextView)view.findViewById(R.id.news_content);

        news_title.setText(title);
        news_content.setText(content);
    }
}
