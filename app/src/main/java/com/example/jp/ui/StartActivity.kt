package com.example.jp.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.eftimoff.androipathview.PathView
import com.example.common.arouter.RouterUrl
import com.example.jp.R


class StartActivity : AppCompatActivity() {
    private var pathView:PathView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pathView = findViewById<View>(R.id.pathView) as PathView

        pathView?.pathAnimator
            ?.delay(100)
            ?.duration(5000)
            ?.listenerEnd(PathView.AnimatorBuilder.ListenerEnd() {

                @Override
                fun onAnimationEnd() {
                    // 监听动画完成之后的跳转实行
                    jump();
                }
            })?.start();

    }

    private fun jump(){
        ARouter.getInstance().build(RouterUrl.APP.Tab).navigation()
    }

}