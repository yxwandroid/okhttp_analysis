package com.wilson.okhttp_analysis.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.wilson.okhttp_analysis.BuildConfig;
import com.wilson.okhttp_analysis.logger.FileFormatStrategy;

import java.io.File;

public class App extends Application {

   public static Context context;
    @Override
    public void onCreate() {
        context =this;
        super.onCreate();
        FormatStrategy fileFormatStrategy = FileFormatStrategy.newBuilder()
                .tag("MyApp V" + BuildConfig.VERSION_NAME)
                .build(getPackageName());
        Logger.addLogAdapter(new DiskLogAdapter(fileFormatStrategy));
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("MyApp V" + BuildConfig.VERSION_NAME)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

    }
}
