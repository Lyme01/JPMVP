package com.example.mine.contract

import com.example.common.base.BaseContract
import com.example.common.bean.DataX
import com.example.common.bean.UserInfoBean

class MineContract:BaseContract {
    interface MineView:BaseContract.BaseView{
        fun getUserInfo(userInfo:UserInfoBean)
        fun logoutSuccess()
    }
    interface CollectView:BaseContract.BaseView{
        fun getCollectArticles(data: DataX)
    }


    interface MinePresenter:BaseContract.BasePresenter{
      fun logout()
      fun getUserInfo()
    }
}