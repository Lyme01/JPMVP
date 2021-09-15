package com.example.common.base

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.R
import com.example.common.arouter.ActionString
import com.example.common.arouter.RouterUrl
import com.example.common.bean.WebBean
import com.example.common.databinding.ActivityBaseWebBinding
import com.example.common.util.TitleBuilder
import com.zhengsr.zweblib.ZWebHelper
import com.zhengsr.zweblib.widght.ZWebChromeClient

@Route(path = RouterUrl.Web.H5)
class BaseWebActivity : BaseActivity<ActivityBaseWebBinding, BaseViewPresenter>() {
      private var bean=WebBean()
    override fun getPresenter(): BaseViewPresenter {
        return BaseViewPresenter()
    }


    override fun initView() {
         bean= intent?.getSerializableExtra(ActionString.H5URL) as WebBean
         TitleBuilder(this@BaseWebActivity)
             .setTitleText(bean.title)
             .setLeftIco(R.drawable.return_icon_1)
             .setLeftIcoListening (leftReturnListener)


        //设置webview
        ZWebHelper.with(this)
            .url(bean.url)
            .parentView(binding.webContain)
            .webChromeClient(ChromeSetting())
            .go()


        val webSettings = ZWebHelper.getWebSettings()

        //缓存,自动缓存了

        //缓存,自动缓存了
        webSettings.setAppCacheEnabled(true)
        webSettings.databaseEnabled = true
        webSettings.databaseEnabled = true
//        if (NetUtils.isNetworkConnected()) {
//            webSettings.cacheMode = WebSettings.LOAD_DEFAULT
//        } else {
//            webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
//        }
//         bean?.url?.let { binding.webview.loadUrl(it) }//加载url
//         binding.webview.addJavascriptInterface(this, "android");//添加js监听 这样html就能调用客户端
//        binding.webview.webChromeClient = webChromeClient;
//        binding.webview.webViewClient = webViewClient;
//        val webSettings: WebSettings = binding.webview.settings
//        webSettings.javaScriptEnabled = true //允许使用js
        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
//        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE //不使用缓存，只从网络获取数据.
//
//        //支持屏幕缩放
//        webSettings.setSupportZoom(true)
//        webSettings.builtInZoomControls = true

        //不显示webview缩放按钮
//        webSettings.setDisplayZoomControls(false);
    }

    override fun setEvents() {

    }

    override fun initDatas() {

    }






     class ChromeSetting : ZWebChromeClient() {
        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = Intent()
            intent.putExtra("bean", bean)
            setResult(RESULT_OK, intent)
        }
        return ZWebHelper.canGoBack(keyCode) || super.onKeyDown(keyCode, event)
    }
 private var  leftReturnListener=View.OnClickListener { finish() }


    /**
     * JS调用android的方法
     * @param str
     * @return
     */
    @JavascriptInterface //仍然必不可少
    fun getClient(str: String) {
        Log.i("ansen", "html调用客户端:$str")
    }

    override fun onDestroy() {
        super.onDestroy()

        //释放资源
        ZWebHelper.onDestroy()

    }
}