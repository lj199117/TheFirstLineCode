package com.hnu.layoutmanager;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hun.userdefindui.R;

/**
 * Created by LJ on 2016-04-08.
 * 自定义控件,再通过类名访问
 */
public class TitleLayout extends LinearLayout {
    Button back = null;
    Button edit = null;
    TextView textView = null;
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.customtitlelayout, this);

        back = (Button)findViewById(R.id.title_back);
        edit = (Button)findViewById(R.id.title_edit);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Will Back",Toast.LENGTH_LONG).show();
                //getContext().startActivity(new Intent(Intent.ACTION_VIEW));
            }
        });

        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Will Edit",Toast.LENGTH_LONG).show();
            }
        });
    }
}
