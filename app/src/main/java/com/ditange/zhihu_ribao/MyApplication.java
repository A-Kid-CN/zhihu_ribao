package com.ditange.zhihu_ribao;

import android.app.Application;

import com.ditange.zhihu_ribao.util.ImageLoaderUtils;

/**
 * Created by ditange on 2016/5/12.
 */
public class MyApplication extends Application {
    private static MyApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtils.initImageConf(getApplicationContext());
        instance = this;

    }


    public static MyApplication getInstance() {
        return instance;
    }

}
