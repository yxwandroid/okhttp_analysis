package com.wilson.okhttp_analysis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpclient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.wilson.okhttp_analysis.application.App;
import com.wilson.okhttp_analysis.bean.VersionBean;
import com.wilson.okhttp_analysis.interceptor.LoggingInterceptor;
import com.wilson.okhttp_analysis.interceptor.NetCacheInterceptor;
import com.wilson.okhttp_analysis.interceptor.OffLineCacheInterceptor;
import com.wilson.okhttp_analysis.utils.ApiUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 最总要的思想是责任链模式
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //异步调用
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过Request构造Http请求：需要传入请求地址url
                Request request = new Request.Builder().url(ApiUtil.APP_UPDATE_URL).build();
                //创建OkHttpClient对象：目的是通过OkHttpClient初始化Call对象
                OkHttpclient client = new OkHttpclient().newBuilder()
                        .addInterceptor(null)
                        .addNetworkInterceptor(null)
                        .build();
                //通过初始化Call对象，来实现网络连接
                Call call = client.newCall(request);
                //网络请求回调，回调方法是在非UI线程进行的

                Callback callback = new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG, "onResponse: Response " + response.body().toString());
                    }
                };
                call.enqueue(callback);

            }
        });


        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        syncRequest();
                        syncRequest();
                        syncRequest();
                    }
                }).start();
            }
        });


    }


    ///创建带缓存的OkHttpClient
    public void buildCacheOkHttpClient() {

        File okHttpCache = new File(App.context.getCacheDir(), "OkHttpCache");
        int cacheSize = 10 * 1024 * 1024;

        Cache cache = new Cache(okHttpCache, cacheSize);
        OkHttpclient okHttpclient = new OkHttpclient()
                .newBuilder()
                .addInterceptor(new OffLineCacheInterceptor())
                .addNetworkInterceptor(new NetCacheInterceptor())
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();


    }

    private void syncRequest() {
        Request build = new Request.Builder().url(ApiUtil.APP_UPDATE_URL).build();
        OkHttpclient okHttpclient = new OkHttpclient.Builder()
//                .addInterceptor(new LoggingInterceptor())
                .build();
        Call call = okHttpclient.newCall(build);
        try {
            Response response = call.execute();
            String s = response.body().string();
            Log.d("MainActivity", s);
            Gson gson = new Gson();
            VersionBean versionBean = gson.fromJson(s, VersionBean.class);
            //   response.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
