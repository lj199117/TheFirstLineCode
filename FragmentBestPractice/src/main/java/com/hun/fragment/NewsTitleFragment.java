package com.hun.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hun.adapter.NewsAdapter;
import com.hun.fragmentbestpractice.NewsContentActivity;
import com.hun.fragmentbestpractice.R;
import com.hun.pojo.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LJ on 2016/4/11.
 */
public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener{
    private List<News> newses = null;
    private ListView news_list = null;
    private  ArrayAdapter<News> newsAdapter = null;
    private boolean isTwoPane;

    public List<News> getNewses() {
        return newses;
    }

    public boolean isTwoPane() {
        return isTwoPane;
    }

    //初始化数据 当碎片和活动建立关联的时候调用
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newses = getNews();
        newsAdapter = new NewsAdapter(activity, R.layout.news_item_layout, newses);
    }
    //为碎片创建视图（加载布局）时调用
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag_layout,container,false);
        news_list = (ListView)view.findViewById(R.id.news_list);
        news_list.setAdapter(newsAdapter);
        news_list.setOnItemClickListener(this);
        return view;
    }
    //确保与碎片相关联的活动一定已经创建完毕的时候调用
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_framelayout) != null) {
            isTwoPane = true; // 可以找到news_content_layout布局时，为双页模式
            //默认显示第一条
            NewsContentFragment newsContentFragment = (NewsContentFragment)getFragmentManager()
                    .findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(newses.get(0).getTitle(),newses.get(0).getContent());
        } else {
            isTwoPane = false; // 找不到news_content_layout布局时，为单页模式
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        News news = newses.get(position);
        if(isTwoPane){
            // 如果是双页模式，则刷新NewsContentFragment中的内容
            NewsContentFragment newsContentFragment = (NewsContentFragment)getFragmentManager()
                    .findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(),news.getContent());
        }else{
            // 如果是单页模式，则直接启动NewsContentActivity
            NewsContentActivity.startAction(getActivity(),news.getTitle(),news.getContent());

        }
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("Succeed in College as a Learning Disabled Student");
        news1.setContent("College freshmen will soon learn to live with a roommate, adjust to a new social scene and survive less-than-stellar dining hall food. Students with learning disabilities will face these transitions while also grappling with a few more hurdles.");
        newsList.add(news1);
        News news2 = new News();
        news2.setTitle("Google Android exec poached by China's Xiaomi");
        news2.setContent("China's Xiaomi has poached a key Google executive involved in the tech giant's Android phones, in a move seen as a coup for the rapidly growing Chinese smartphone maker.");
        newsList.add(news2);
        return newsList;
    }
}
