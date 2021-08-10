package com.example.common.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.alibaba.android.arouter.launcher.ARouter;

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";

    public static Context appContext;

    public static Handler HANDLER = new Handler(Looper.myLooper());

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();     // Print log
        ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        ARouter.init(this);
        appContext = getApplicationContext();
//        boolean isNight = SpfUtils.get(Constant.KEY_IS_NIGHT,false);
//        if (isNight){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
//    }
    }
}
