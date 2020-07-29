package com.wilson.okhttp_analysis.disklrucache;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wilson.okhttp_analysis.R;

public class DiskLruCacheActivity extends AppCompatActivity {


    private TextView mText;
    private DiskLruCacheUtil mDiskLruCacheUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disk_cache_layout);
        mText = (TextView) findViewById(R.id.text);

        UserBean userBean = new UserBean("张三", 24);
        mDiskLruCacheUtil = new DiskLruCacheUtil(this, "DISK_LRU_CACHE");
        mDiskLruCacheUtil.put("STRING", "我是string类型");
        mDiskLruCacheUtil.put("object", userBean);
    }

    public void getString(View view) {
        String string = mDiskLruCacheUtil.getStringCache("STRING");
        mText.setText(string);

    }

    public void getObject(View view) {
        UserBean userBean = mDiskLruCacheUtil.getObjectCache("object");
        if (userBean != null)
            mText.setText("姓名:" + userBean.getName() + "年龄:" + userBean.getAge());
    }
}
