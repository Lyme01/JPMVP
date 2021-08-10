package com.example.common.base

import com.example.common.util.Constant
import com.example.common.util.SpfUtils
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference
import java.util.*

/**
 * Created by le on 2018/8/9.
 */
open class BasePresenter<V : BaseContract.BaseView> : BaseContract.BasePresenter {
    protected var mView: V? = null
    private var weakView: WeakReference<BaseContract.BaseView>? = null
    protected var listReqs: MutableList<Disposable> =
        ArrayList()

    /**
     * 界面绑定，关联 MVP View
     *
     * @param view MVP View
     */
    override fun attachView(view: BaseContract.BaseView) {
        weakView = WeakReference(view)
        mView = weakView!!.get() as V?
    }


    /**
     * 解除绑定，去除 MVP View 引用
     */
    override fun detachView() {
        if (mView != null) {
            mView = null
            weakView!!.clear()
            weakView = null
        }
    }

    override fun cancelAll() {
        for (disposable in listReqs) {
            disposable.dispose()
        }
    }

    /**
     * 将网络请求添加到 RxJava2 生命周期
     */
    protected fun addReqs(disposable: Disposable) {
        listReqs.add(disposable)
    }

    /**
     * 是否已经登录
     * @return
     */
    open fun isLogin(): Boolean {
        return SpfUtils.get(Constant.KEY_IS_LOGIN, false)
    }

    open fun setLogin(isLogin: Boolean) {
        SpfUtils.put(Constant.KEY_IS_LOGIN, isLogin)
    }

    open fun getUserName(): String? {
        return SpfUtils.get(Constant.KEY_USERNAME, "")
    }

    open fun setUserName(userName: String?) {
        SpfUtils.put(Constant.KEY_USERNAME, userName)
    }

    open fun getPassword(): String? {
        return SpfUtils.get(Constant.KEY_PASSWORD, "")
    }

    open fun setPassword(password: String?) {
        SpfUtils.put(Constant.KEY_PASSWORD, password)
    }

}