package com.hun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hun.fragmentbestpractice.R;
import com.hun.pojo.News;

import java.util.List;

/**
 * Created by LJ on 2016-04-09.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    int newsResource;
    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        this.newsResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view = null;
        TextView textView = null;
        NewsViewHolder newsViewHolder = null;
        if(convertView == null){
            view = (LinearLayout) LayoutInflater.from(getContext()).inflate(this.newsResource, null);
            textView = (TextView)view.findViewById(R.id.news_title);
            newsViewHolder = new NewsViewHolder();
            newsViewHolder.textView = textView;
            view.setTag(newsViewHolder);
        }else{
            view = convertView;
            newsViewHolder = (NewsViewHolder)view.getTag();
        }
        textView = newsViewHolder.textView;
        textView.setText(news.getTitle());

        return view;
    }

    class NewsViewHolder{
        TextView textView;

    }
}
