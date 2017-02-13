package com.qf.sxy.bitmaputils;

import android.app.Application;
import android.graphics.Bitmap;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.cache.MD5FileNameGenerator;

/**
 * Created by sxy on 2016/11/1.
 */
public class MyApp extends Application {

    private static MyApp myapp;
    public static MyApp getMyApp(){
        return myapp;
    }

    private BitmapUtils bitmapUtils;

    public BitmapUtils getBitmapUtils(){
        return bitmapUtils;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        this.myapp = this;
        initBitmapUtis();
    }

    private void initBitmapUtis() {

        /**
         * 参数1:上下文
         * 参数2:缓存路径
         * 参数3:内存缓存的大小
         * 参数4:磁盘缓存的大小
         */
          bitmapUtils = new BitmapUtils(
               this,
                getCacheDir().getAbsolutePath(),
                10*1024*1024,
                20*1024*1024
        );
        bitmapUtils.configThreadPoolSize(3);//设置下载线程的数量  1-5
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);//设置图片的解码
        bitmapUtils.configDefaultConnectTimeout(3*1000);//默认的链接时间
        bitmapUtils.configMemoryCacheEnabled(true);//是否开启内存缓存
        bitmapUtils.configDiskCacheEnabled(true);//是否开启磁盘缓存
        bitmapUtils.configDiskCacheFileNameGenerator(new MD5FileNameGenerator());//文件命名器
        bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_launcher);//下载时展示的图片
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.ic_launcher);//下载失败展示的图片
    }
}
