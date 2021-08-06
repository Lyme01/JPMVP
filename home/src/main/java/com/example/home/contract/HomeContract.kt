package com.example.home.contract

import com.example.common.base.BaseContract
import com.example.common.bean.BannerBean
import com.example.common.bean.DataX
import com.example.common.bean.NewsBean


interface HomeContract :BaseContract {
    interface HomeView : BaseContract.BaseView {
//        fun testView(list: List<DataBean>)
//        fun testTab(tabBean: TabBean)
//          fun  showNews(newsBean: NewsBean)
        fun showNews(articles: NewsBean<List<DataX>>?)
        fun loadArticle(articles: NewsBean<List<DataX>>?)
        fun reloadArticle(articles: NewsBean<List<DataX>>?)
        fun showBanner(banners:List<BannerBean>)
    }

    interface SearchView:BaseContract.BaseView {
     fun  getSearch(articles: NewsBean<List<DataX>>?)
    }

    interface Presenter  {
       fun getNews()
        fun loadMore()
        fun reload()
       fun  getBanner()
       fun search(word:String)

    }
}