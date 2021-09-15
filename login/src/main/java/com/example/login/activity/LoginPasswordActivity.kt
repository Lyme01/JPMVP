package com.example.login.activity

import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseActivity
import com.example.common.util.TitleBuilder
import com.example.login.contract.LoginContract
import com.example.login.databinding.ActivityLoginBinding
import com.example.login.present.LoginPresent
@Route(path = RouterUrl.Login.Login)
class LoginPasswordActivity : BaseActivity<ActivityLoginBinding,LoginPresent>(),LoginContract.View {



    override fun getPresenter(): LoginPresent {
        return LoginPresent();
    }

    override fun initView() {
        TitleBuilder(this).setTitleText("登录")
    }

    override fun setEvents() {

    }

    override fun initDatas() {

    }

    private fun login(username: String, password: String) {
        mPresenter?.login(username,password)
    }



    override fun showLogin(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show();
    }

    fun Click(view: View) {
        when(view){
            binding.login->login(binding.username.text.toString(), binding.password.text.toString())
            binding.register->ARouter.getInstance().build(RouterUrl.Login.Register).navigation()
        }
    }
}