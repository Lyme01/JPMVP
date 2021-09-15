package com.example.mine.contract

import com.example.common.base.BaseContract
import com.example.common.bean.DataX
import com.example.common.bean.NewsBean
import com.example.common.bean.UserInfoBean

class MineContract:BaseContract {
    interface MineView:BaseContract.BaseView{
        fun getUserInfo(userInfo:UserInfoBean)
        fun logoutSuccess()
    }
    interface CollectView:BaseContract.BaseView{
        fun getCollectArticles(maxPage:Int,data: NewsBean<List<DataX>>,isRefresh: Boolean)
    }


    interface MinePresenter:BaseContract.BasePresenter{
      fun logout()
      fun getUserInfo()
      fun getCollect(page:Int,isRefresh: Boolean)
    }
}