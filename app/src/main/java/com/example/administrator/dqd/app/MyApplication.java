package com.example.administrator.dqd.app;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"7UwhYmoLLE5l7B5P3g4amEiK-gzGzoHsz","bAT2qm9Ob5bBHQ9xfy8ILKSK");
//        AVOSCloud.setDebugLogEnabled(true);
    }
}
