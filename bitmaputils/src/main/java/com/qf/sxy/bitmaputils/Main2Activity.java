package com.qf.sxy.bitmaputils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main2)
public class Main2Activity extends AppCompatActivity {

    @ViewInject(R.id.iv)
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main2);
        ViewUtils.inject(this);
    }


    public void MyClick(View view) {
        BitmapUtils bitmapUtils = new BitmapUtils(Main2Activity.this);
        bitmapUtils.display(iv,"assets/img/a3.jpg");
    }
}
