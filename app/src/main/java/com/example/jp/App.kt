package com.example.jp

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

class App : Application() {

    private var mInstance: App? = null
    override fun onCreate() {
        super.onCreate()
                  // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        ARouter.init(this)
    }
    fun getInstance(): Context? {
        if (mInstance == null) {
            mInstance =App()
        }
        return mInstance
    }


}