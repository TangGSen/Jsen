package com.sen.utils;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Sen on 2016/1/6.
 */
public class SenApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initBmob();
    }

    private void initBmob() {
        Bmob.initialize(this,"6d66f030aaf8a34c92f5b13d13b3d0dc");
    }
}
