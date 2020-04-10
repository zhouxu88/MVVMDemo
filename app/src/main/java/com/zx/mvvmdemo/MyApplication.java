package com.zx.mvvmdemo;

import android.app.Application;
import android.content.Context;

/**
 * @author zhouxu
 * @descripe
 * @e-mail 374952705@qq.com
 * @time 2020/4/9
 */


public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

//        MultiDex.install(this);
    }
}
