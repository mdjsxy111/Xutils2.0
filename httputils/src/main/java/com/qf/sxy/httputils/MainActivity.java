package com.qf.sxy.httputils;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;

/**
 * HttpUtils 进行网络请求  有get请求   post请求(文件上传)  文件下载
 */
public class MainActivity extends AppCompatActivity {



    //get请求
    private String pathGet = "http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&page=1&limit=20";
    //post 请求  http://mrobot.pcauto.com.cn/v2/cms/channels/1?pageNo=1&pageSize=20&serialIds=2143,3404&v=4.0.0
    //post提交的字段：pageNo=1&pageSize=20&serialIds=2143,3404&v=4.0.0
    String pathPost = "http://mrobot.pcauto.com.cn/v2/cms/channels/1?";

    //apk下载地址
    private String pathApk = "http://down.72g.com/upload/app/201407/201407150923238621.apk";

    /**
     * 2.6.14版本的  引入的jar  org.apache.http.legacy.jar 解决5.0以后不能使用HttpClient 对象
     * 底层用的HttpClient
     */
    private HttpUtils httpUtils;//声明HttpUtils对象

    private ProgressDialog progressDialog;

    private HttpHandler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取HttpUtils 对象
         httpUtils = new HttpUtils();
        initProgressDialog();
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("下在Apk");
        progressDialog.setMessage("拼命下载中...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);

        progressDialog.setButton(ProgressDialog.BUTTON1, "取消下载", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //取消下载
                ///????????
                if(h!=null){
                    //取消下载
                    h.cancel();
                }
            }
        });
    }

    //使用HttpUtils下载
    public void getStringClick(View view) {
        /**
         * HttpUtils 的get请求
         * 参数1:请求方式
         * 参数2:请求地址
         * 参数3:请求的回调   包括成功的回调   失败的回调
         */
        httpUtils.send(HttpRequest.HttpMethod.GET,pathGet, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {

                Log.e("AAA","=onSuccess=>"+responseInfo.result.toString());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.e("AAA","=onFailure=>"+s);
            }
        });
    }

    /**
     * Post请求
     * @param view
     */
    public void getPostClick(View view) {
        //pageNo=1&pageSize=20&serialIds=2143,3404&v=4.0.0
        RequestParams params = new RequestParams();
       // params.addBodyParameter(key,file);//用于上传文件
        params.addBodyParameter("pageNo","1");
        params.addBodyParameter("pageSize","20");
        params.addBodyParameter("serialIds","2143,3404");
        params.addBodyParameter("v","4.0.0");
        httpUtils.send(HttpRequest.HttpMethod.POST, pathPost, params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.e("AAA","=Post=>"+responseInfo.result.toString());
                Log.e("AAA","=Post=>"+responseInfo.statusCode);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    /**
     * HttpUtils下载
     * @param view
     */
    public void getDownloadClick(View view) {
        /**
         * 参数1:请求地址
         * 参数2:本地的存储位置  /sdcard/a.apk
         * 参数3:是否可以继续下载  断点下载
         * 参数4:下载之后是否重命名
         * 参数5:请求的回调接口
         */
         h = httpUtils.download(pathApk, "/sdcard/b.apk", true, true, new RequestCallBack<File>() {

            //开始下载时  初始化操作
            @Override
            public void onStart() {
                super.onStart();
                progressDialog.show();
            }

            //下载取消时
            @Override
            public void onCancelled() {
                super.onCancelled();
                progressDialog.dismiss();
                Log.e("AAA","=onCancelled=>");
            }

            //当前的进度
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                //展示下载进度
                progressDialog.setProgress((int) (current*100/total));
            }

            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                progressDialog.dismiss();
                File file = responseInfo.result;
                //下载成功 有数据
                if(file!=null){
                    AutoInstall.setUrl(file.getAbsolutePath());
                    AutoInstall.install(MainActivity.this);
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                progressDialog.dismiss();
            }
        });
    }
}
