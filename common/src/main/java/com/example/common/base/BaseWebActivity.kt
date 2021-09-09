package com.example.common.base

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
import com.example.common.arouter.ActionString
import com.example.common.arouter.RouterUrl
import com.example.common.bean.WebBean
import com.example.common.databinding.ActivityBaseWebBinding
import com.example.common.util.TitleBuilder

@Route(path = RouterUrl.Web.H5)
class BaseWebActivity : BaseActivity<ActivityBaseWebBinding, BaseViewPresenter>() {
      private var bean=WebBean()
    override fun getPresenter(): BaseViewPresenter {
        return BaseViewPresenter()
    }


    override fun initView() {
         bean= intent?.getSerializableExtra(ActionString.H5URL) as WebBean
         TitleBuilder(this@BaseWebActivity).setTitleText(bean.title)
         bean?.url?.let { binding.webview.loadUrl(it) }//加载url
         binding.webview.addJavascriptInterface(this, "android");//添加js监听 这样html就能调用客户端
        binding.webview.webChromeClient = webChromeClient;
        binding.webview.webViewClient = webViewClient;
        val webSettings: WebSettings = binding.webview.settings
        webSettings.javaScriptEnabled = true //允许使用js
        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE //不使用缓存，只从网络获取数据.

        //支持屏幕缩放
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true

        //不显示webview缩放按钮
//        webSettings.setDisplayZoomControls(false);
    }

    override fun setEvents() {

    }

    override fun initDatas() {

    }

    private val webViewClient: WebViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) { //页面加载完成
            binding.progressbar.visibility = View.GONE
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) { //页面开始加载
            binding.progressbar.visibility = View.VISIBLE
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
            Log.i("ansen", "拦截url:$url")
            if (url == "http://www.google.com/") {
                Toast.makeText(this@BaseWebActivity, "国内不能访问google,拦截该url", Toast.LENGTH_LONG).show()
                return true //表示我已经处理过了
            }
            return super.shouldOverrideUrlLoading(view, url)
        }
    }


    private val webChromeClient: WebChromeClient = object : WebChromeClient() {
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        override fun onJsAlert(
            webView: WebView,
            url: String?,
            message: String?,
            result: JsResult
        ): Boolean {
            val localBuilder: AlertDialog.Builder = AlertDialog.Builder(webView.context)
            localBuilder.setMessage(message).setPositiveButton("确定", null)
            localBuilder.setCancelable(false)
            localBuilder.create().show()

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm()
            return true
        }

        //获取网页标题
        override fun onReceivedTitle(view: WebView?, title: String) {
            super.onReceivedTitle(view, title)
            Log.i("ansen", "网页标题:$title")

        }

        //加载进度回调
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            binding.progressbar.progress = newProgress
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.i("ansen", "是否有上一个页面:" + binding.webview.canGoBack())
        if (binding.webview.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) { //点击返回按钮的时候判断有没有上一页
            binding.webview.goBack() // goBack()表示返回webView的上一页面
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

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
        binding.webview.destroy()

    }
}