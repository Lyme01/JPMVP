package com.example.login.contract

import android.content.Context
import com.example.common.base.BaseContract
import com.example.common.http.HttpMap

class LoginContract:BaseContract {

    interface View : BaseContract.BaseView {
         fun showLogin(msg:String)
    }

    interface Presenter  {
        /**登录*/
        fun login(username:String,password:String)
        fun register(username: String,password: String,repassword:String)

    }
}