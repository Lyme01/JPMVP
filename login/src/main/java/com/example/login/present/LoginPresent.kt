package com.example.login.present

import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseBean
import com.example.common.base.BasePresenter
import com.example.common.bean.UserBean
import com.example.common.http.BaseResourceObserver
import com.example.login.contract.LoginContract
import com.example.login.model.LoginModel
import io.reactivex.disposables.Disposable

class LoginPresent: BasePresenter<LoginContract.View>(), LoginContract.Presenter{
    var model = LoginModel()
    override fun login(username:String,password:String) {
        model.login(username,password)?.subscribe(object : BaseResourceObserver<BaseBean<UserBean>>(){
            override fun onNext(t: BaseBean<UserBean>) {
             if (t.errorCode==0){
                 ARouter.getInstance().build(RouterUrl.APP.Tab).navigation()
             }else{
                 mView?.showLogin(t.errorMsg)
             }
            }

            override fun onSubscribe(d: Disposable) {
            }



        })
    }




}