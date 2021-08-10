package com.example.common.model

import com.example.common.base.BaseBean
import com.example.common.bean.BannerBean
import com.example.common.bean.DataX
import com.example.common.bean.HotKeyBean
import com.example.common.bean.NewsBean
import com.example.common.http.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeModel {

    fun getNews(num:Int) : Observable<BaseBean<NewsBean<List<DataX>>>> {
        return  Api.getInstance().getApiService().getNews(num).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
//
    fun getBanner(): Observable<BaseBean<List<BannerBean>>> {
        return Api.getInstance().getApiService().getBanner().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun serach(page:Int,word:String):Observable<BaseBean<NewsBean<List<DataX>>>> {
        return  Api.getInstance().getApiService().search(page,word).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun addArticle(id:Int):Observable<BaseBean<NewsBean<List<DataX>>>> {
        return  Api.getInstance().getApiService().addArticle(id)!!.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun removeArticle(id:Int):Observable<BaseBean<NewsBean<List<DataX>>>> {
        return  Api.getInstance().getApiService().removeArticle(id)!!.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun hotKey():Observable<BaseBean<List<HotKeyBean>>> {
        return  Api.getInstance().getApiService().hotKey().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

}