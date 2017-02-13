package com.qf.sxy.myannotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @UI(id = R.id.tv, click = "test")
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注入注解工具
        ParseUI.handlerUI(this);

        tv.setText("自己写的注解工具");
    }

    /**
     * tv的点击事件
     *
     * @param view
     */
    public void test(View view) {
        Toast.makeText(MainActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
    }
}
