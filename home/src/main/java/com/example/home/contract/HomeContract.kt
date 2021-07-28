package com.example.home.contract

import com.example.common.base.BaseContract
import com.example.common.bean.NewsBean


interface HomeContract :BaseContract {
    interface View : BaseContract.BaseView {
//        fun testView(list: List<DataBean>)
//        fun testTab(tabBean: TabBean)
          fun  showNews(newsBean: NewsBean)
    }

    interface Presenter  {
//        fun testPresenter()
//        fun getTab()
       fun getNews()

    }
}