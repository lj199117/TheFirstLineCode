package com.hun.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    Button button1 = null;
    Button button2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                /*
                1. 创建待添加的碎片实例。
                2. 获取到FragmentManager，在活动中可以直接调用getFragmentManager()方法得到。
                3. 开启一个事务，通过调用beginTransaction()方法开启。
                4. 向容器内加入碎片，一般使用replace()方法实现，需要传入容器(布局)的id 和待添加的碎
                    片实例。
                5. 提交事务，调用commit()方法来完成。*/
                ThirdFragment thirdFragment = new ThirdFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.right_layout,thirdFragment);
                fragmentTransaction.commit();
                break;
            case R.id.button2:
                RightFragment rightFragment = new RightFragment();
                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.right_layout,rightFragment);
                fragmentTransaction1.addToBackStack(null);//将此碎片加载back栈中
                fragmentTransaction1.commit();
                break;
            default:break;
        }
    }
}
