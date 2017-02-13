package com.qf.sxy.bitmaputils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.core.BitmapSize;

public class Main3Activity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        iv = ((ImageView) findViewById(R.id.iv));
    }

    public void MyClick(View view) {
//        BitmapUtils bitmapUtils = new BitmapUtils(Main3Activity.this);
        //方式一
        //bitmapUtils.display(iv,"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3238412508,2580254954&fm=116&gp=0.jpg");

        //方式2:对图片进行配置
        BitmapDisplayConfig config = new BitmapDisplayConfig();
        config.setBitmapMaxSize(new BitmapSize(50, 50));//设置图片的最大宽和高
        config.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));//加载中默认展示的图片
        config.setLoadFailedDrawable(getResources().getDrawable(R.mipmap.ic_launcher));//加载失败展示的图片
       // bitmapUtils.display(iv, "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3207227248,3094700222&fm=23&gp=0.jpg", config);

        //方式3

//        MyApp.getMyApp().getBitmapUtils().display(iv, "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1693555025,1847291683&fm=111&gp=0.jpg", new BitmapLoadCallBack<ImageView>() {
//            @Override
//            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
//                iv.setImageBitmap(bitmap);
//            }
//
//            @Override
//            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {
//
//            }
//        });

        //方式4
        MyApp.getMyApp().getBitmapUtils().display(iv,
                "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=83359233,1287419843&fm=58",
                config,
                new BitmapLoadCallBack<ImageView>() {
                    @Override
                    public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                       iv.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

                    }
                });


    }
}
