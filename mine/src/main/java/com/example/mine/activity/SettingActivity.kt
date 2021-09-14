package com.example.mine.activity


import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseActivity

import com.example.mine.databinding.ActivitySettingBinding
import com.example.mine.presenter.MinePresenter
@Route(path = RouterUrl.Mine.Setting)
class SettingActivity : BaseActivity<ActivitySettingBinding,MinePresenter>(), View.OnClickListener{
    override fun getPresenter(): MinePresenter {
        return MinePresenter()
    }

    override fun initView() {
        binding.logout.setOnClickListener(this)
    }

    override fun setEvents() {

    }

    override fun initDatas() {

    }

    override fun onClick(v: View?) {
      mPresenter?.logout()
        finish()
    }

}