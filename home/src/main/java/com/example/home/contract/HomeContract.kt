package com.example.home.contract

import com.example.common.base.BaseContract
import com.example.common.bean.BannerBean
import com.example.common.bean.DataX
import com.example.common.bean.HotKeyBean
import com.example.common.bean.NewsBean


interface HomeContract :BaseContract {
    interface HomeView : BaseContract.BaseView {
        fun showNews(maxPage: Int,articles: NewsBean<List<DataX>>?,isRefresh: Boolean)
        fun showBanner(banners: List<BannerBean>)
        fun addArticleSuccess(position: Int,data: DataX)
        fun removeArticleSuccess(position: Int,data: DataX)

    }

    interface SearchView:BaseContract.BaseView {
     fun  getHotKey(hotKeys: List<HotKeyBean>)
    }

    interface SearchDetailView:BaseContract.BaseView {
        fun addSuccess(position: Int,data: DataX)
        fun removeSuccess(position: Int,data: DataX)
        fun getSearchData(maxPage: Int, datas: NewsBean<List<DataX>>?, isRefresh: Boolean)
    }

    interface Presenter  {
       fun getNews(page: Int,isRefresh: Boolean)

       fun  getBanner()
       fun search(page: Int, word: String, isRefresh: Boolean)
       fun removeArticle(position: Int, data: DataX)
       fun addArticle(position: Int, data: DataX)
       fun hotKey()

    }
}