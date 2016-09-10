package com.weibo.customviewdemo.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by 巴巴 on 2016/9/10.
 */
public class MainApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext(){
        return context;
    }

}
