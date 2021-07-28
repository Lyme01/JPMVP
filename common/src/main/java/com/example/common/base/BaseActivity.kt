package com.example.common.base


import android.app.Activity

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.dylanc.viewbinding.inflateBindingWithGeneric
import com.example.common.R
import com.jaeger.library.StatusBarUtil
import java.lang.ref.WeakReference


/**
 * @author ludoven
 */
abstract class BaseActivity<VB : ViewBinding, P : BaseContract.BasePresenter> : AppCompatActivity(),
    BaseContract.BaseView {
    protected var mPresenter: P? = null
    protected lateinit var binding: VB
    /** 当前界面 Context 对象*/
    protected lateinit var mContext: AppCompatActivity
    private var weakReference: WeakReference<Activity>? = null
  //    是否显示状态栏
    open var isShowStatus:Boolean?=true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext=this
     //将activity添加到管理列表中
        if (weakReference == null) {
            weakReference= WeakReference<Activity>(this);
        }

        ActivityManager.getInstance().addActivity(weakReference!!.get());
        // 设置要不要显示状态栏
        setStatusShow()
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent))
        mPresenter = getPresenter()
        mPresenter?.attachView(this)
        binding = this.inflateBindingWithGeneric(layoutInflater)
        setContentView(binding.root)
        initView()
        initDatas()
        setEvents()
    }

    abstract fun getPresenter(): P
    protected abstract fun initView()
    override fun showError(e: Throwable?) {

    }

     fun setStatusShow(){
        if (isShowStatus==false){
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
            /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
            requestWindowFeature(Window.FEATURE_NO_TITLE);

        }
    }
    //监听事件
    protected abstract fun setEvents()

    //初始化数据
    protected abstract fun initDatas()






    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.cancelAll()
            mPresenter!!.detachView()
        }
        //将activity从列表中移除
        ActivityManager.getInstance().removeActivity(weakReference?.get());
        System.gc()

    }

}