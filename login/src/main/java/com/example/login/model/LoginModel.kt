package com.example.login.model

import com.example.common.base.BaseBean
import com.example.common.bean.UserBean
import com.example.common.http.Api
import com.example.common.http.HttpFormat
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginModel {

    fun login(username:String,password:String) : Observable<BaseBean<UserBean>>? {
        return  Api.getInstance().getApiService().login(username,password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()
        )
    }

}