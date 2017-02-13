package com.qf.sxy.bitmaputils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;

/**
 * 加载本地图片
 * 1,实例化BitmapUtils对象
 * 2,使用BitmapUtils 加载图片
 */
public class MainActivity extends AppCompatActivity {

    private ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = ((ImageView) findViewById(R.id.iv));
    }

    public void MyClick(View view) {

        BitmapUtils bitmapUtils = new BitmapUtils(MainActivity.this);
        bitmapUtils.display(iv,"/sdcard/gou.jpg");
    }
}
