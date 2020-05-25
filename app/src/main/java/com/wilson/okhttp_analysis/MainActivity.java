package com.wilson.okhttp_analysis;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.wilson.okhttp_analysis.utils.ApiUtil;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
   private final String  TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过Request构造Http请求：需要传入请求地址url
                Request request = new Request.Builder().url(ApiUtil.APP_UPDATE_URL).build();
                //创建OkHttpClient对象：目的是通过OkHttpClient初始化Call对象
                OkHttpClient client = new OkHttpClient();
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
//                Callback callback = new Callback() {
//                    @Override
//                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                        Log.d(TAG, "onResponse: Response " +response.body().toString());
//                    }
//
//                    @Override
//                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
//
//                    }
//
//                };
                call.enqueue(callback);

            }
        });


    }
}
