package com.hun.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hun.pojo.Msg;
import com.hun.uibestpractice.R;

import java.util.List;

/**
 * Created by LJ on 2016-04-08.
 */
public class ListViewAdapter extends ArrayAdapter<Msg> {
    int layoutResource;
    public ListViewAdapter(Context context, int resource, List<Msg> objects) {
        super(context, resource, objects);
        this.layoutResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = getItem(position);
        View view = null;
        ViewMsgHolder viewMsgHolder= null;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(layoutResource,null);
            //convertView = view;  convertView本身就是缓存 无需赋值
            LinearLayout leftLayout = (LinearLayout)view.findViewById(R.id.left_layout);
            LinearLayout rightLayout = (LinearLayout)view.findViewById(R.id.right_layout);
            TextView leftMsg = (TextView)view.findViewById(R.id.left_msg);
            TextView rightMsg = (TextView)view.findViewById(R.id.right_msg);
            viewMsgHolder = new ViewMsgHolder();
            viewMsgHolder.leftLayout = leftLayout;
            viewMsgHolder.rightLayout = rightLayout;
            viewMsgHolder.leftMsg = leftMsg;
            viewMsgHolder.rightMsg = rightMsg;

            view.setTag(viewMsgHolder);
        }else{
            view = convertView;//存的布局缓存
            viewMsgHolder = (ViewMsgHolder)view.getTag();//存的控件缓存
        }

        if(Msg.TYPE_RECEIVED == msg.getType()) {
            viewMsgHolder.leftLayout.setVisibility(View.VISIBLE);
            viewMsgHolder.rightLayout.setVisibility(View.GONE);

            viewMsgHolder.leftMsg.setText(msg.getContent());
        }else{
            viewMsgHolder.rightLayout.setVisibility(View.VISIBLE);
            viewMsgHolder.leftLayout.setVisibility(View.GONE);

            viewMsgHolder.rightMsg.setText(msg.getContent());
        }
        return view;
    }

    class ViewMsgHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
    }
}
