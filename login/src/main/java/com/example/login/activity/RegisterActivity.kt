package com.example.login.activity


import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseActivity

import com.example.login.contract.LoginContract
import com.example.login.databinding.ActivityRegisterBinding
import com.example.login.present.LoginPresent

@Route(path = RouterUrl.Login.Register)
class RegisterActivity :BaseActivity<ActivityRegisterBinding,LoginPresent>(),LoginContract.View {


    override fun getPresenter(): LoginPresent {
     return LoginPresent()
    }

    override fun initView() {

    }

    override fun setEvents() {

    }

    override fun initDatas() {

    }

    override fun showLogin(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show();
    }

    fun Click(view: View) {
        when(view){
            binding.register->{
                mPresenter?.register(binding.etUsername.text.toString(),binding.etPassword.text.toString(),binding.etRepassword.text.toString())
            }
        }

    }
}