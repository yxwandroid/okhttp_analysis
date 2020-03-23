package com.wilson.okhttp_analysis;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;


import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient client = new OkHttpClient().newBuilder().build();//.addInterceptor();

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);//创建一个Builder对象
//        builder.setIcon(R.drawable.ic_launcher_foreground);
//        builder.setTitle("标题");
//        builder.setMessage("信息");
//        builder.setPositiveButton("确定",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//        AlertDialog alertDialog = builder.create();//创建AlertDialog对象
//        alertDialog.show();//展示AlertDialo


//        lruCache.get();
//
//        new HashMap<>()



    }

    private LruCache<String, Bitmap> lruCache;


}
