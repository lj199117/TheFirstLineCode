package com.hnu.listviewtest;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hnu.customadapter.FruitAdapter;
import com.hnu.pojo.Fruit;
import com.hun.listviewtest.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ListView listView = null;
    ImageView imageView = null;
    List<Fruit> fruitList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        fruitList = new ArrayList<Fruit>();
        fruitList = initFruitList();
        ListAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruitlayout,fruitList);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,fruitList.get(position).getName(),Toast.LENGTH_SHORT).show();
                ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.apple_pic);
            }
        });
    }

    private List<Fruit> initFruitList() {
        List<Fruit> fruitList = new ArrayList<Fruit>();
        Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
        fruitList.add(banana);
        Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
        fruitList.add(orange);
        Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
        fruitList.add(watermelon);
        Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
        fruitList.add(pear);
        Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
        fruitList.add(grape);
        Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
        fruitList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
        fruitList.add(strawberry);
        Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
        fruitList.add(cherry);
        Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
        fruitList.add(mango);
        return fruitList;
    }


}
