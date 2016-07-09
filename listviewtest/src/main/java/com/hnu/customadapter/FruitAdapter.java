package com.hnu.customadapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnu.pojo.Fruit;
import com.hun.listviewtest.R;

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId,
                        List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view;
        ViewFruitHolder viewHolder;//通过内部类对实例进行缓存
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewFruitHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
            viewHolder.textView = (TextView) view.findViewById(R.id.textView);
            view.setTag(viewHolder);//保存缓存
        } else {
            view = convertView;//通过convertView对布局进行缓存
            viewHolder = (ViewFruitHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(fruit.getResourceID());
        viewHolder.textView.setText(fruit.getName());
        return view;
    }
    //通过内部类对实例进行缓存
    class ViewFruitHolder {
        ImageView imageView;
        TextView textView;
    }

}



