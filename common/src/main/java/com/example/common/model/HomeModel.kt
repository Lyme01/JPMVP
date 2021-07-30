package com.example.common.model

import com.example.common.base.BaseBean
import com.example.common.bean.NewsBean
import com.example.common.http.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeModel {

    fun getNews(num:Int) : Observable<BaseBean<NewsBean>> {
        return  Api.getInstance().getApiService().getNews(num).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

}