package com.example.jp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.eftimoff.androipathview.PathView
import com.example.jp.R


class StartActivity : AppCompatActivity() {
    private val pathView = findViewById<View>(R.id.pathView) as PathView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        fun jump() {

        }
        pathView?.pathAnimator
            .delay(100)
            .duration(5000)
            .listenerEnd(PathView.AnimatorBuilder.ListenerEnd() {

                @Override fun onAnimationEnd() {
                    // 监听动画完成之后的跳转实行
                    jump();
                }
            }).start();

    }

}