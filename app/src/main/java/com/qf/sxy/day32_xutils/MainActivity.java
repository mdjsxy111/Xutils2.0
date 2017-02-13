package com.qf.sxy.day32_xutils;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 使用VIewUtils
 * 1,注入注解工具 (Activity,Fragment,Adapter)
 * 2,使用注解工具进行注入(UI(布局,控件),资源类(字符串,图片,动画...),事件(20多种))
 */
@ContentView(R.layout.activity_main)//将布局资源注入
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.tv)//注入控件
    private TextView tv;

    @ViewInject(R.id.btn)
    private Button btn;

    @ResInject(id = R.mipmap.ic_launcher,type = ResType.Drawable)//注入资源
    private Drawable drawable;

    @ResInject(id = R.string.hello,type = ResType.String)
    private String hello;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //1,注入注解工具
        ViewUtils.inject(this);

       // tv = (TextView) findViewById(R.id.tv);

        tv.setText("快过年了...");

        btn.setText(hello);

        btn.setBackground(drawable);



    }

    //注入事件   note:参数必须和之前的保持一致
    @OnClick({R.id.btn,R.id.btn1,R.id.btn2})
    public void MyClick(View v){

        switch (v.getId()){
            case R.id.btn:
//                Toast.makeText(MainActivity.this,"我是事件",Toast.LENGTH_SHORT).show();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.ll_container,new ViewUtilsFragment())
                        .commit();
                break;
            case R.id.btn1:
                Toast.makeText(MainActivity.this,"我是事件1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(MainActivity.this,"我是事件2",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
