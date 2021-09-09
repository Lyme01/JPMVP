package com.example.mine.contract

import com.example.common.base.BaseContract
import com.example.common.bean.UserInfoBean

class MineContract:BaseContract {
    interface MineView:BaseContract.BaseView{
        fun getUserInfo(userInfo:UserInfoBean)
    }

    interface MinePresenter:BaseContract.BasePresenter{
      fun logout()
      fun getUserInfo()
    }
}