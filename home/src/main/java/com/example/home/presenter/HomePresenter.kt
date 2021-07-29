package com.example.home.presenter


import android.util.Log
import com.example.common.base.BasePresenter
import com.example.common.base.BaseBean
import com.example.common.http.Api


import com.example.common.http.BaseResourceObserver
import com.example.common.bean.NewsBean
import com.example.common.model.HomeModel
import com.example.home.contract.HomeContract

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomePresenter : BasePresenter<HomeContract.View>(),
    HomeContract.Presenter {
    var model=HomeModel()
//    override fun getNews() {
//        model.getNews().subscribe(object : BaseResourceObserver<List<BaseBean<NewsBean>>>() {
//                override fun onSubscribe(d: Disposable) {
//                }
//
//
//            override fun onNext(t:List<BaseBean<NewsBean>>) {
//                TODO("Not yet implemented")
//            }
//        })
//    }
    override fun getNews() {
        Api.getInstance().getApiService()
            .getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseResourceObserver<BaseBean<NewsBean>>() {
                override fun onSubscribe(d: Disposable) {

                }


                override fun onNext(t: BaseBean<NewsBean>) {

                    t.data?.let { mView?.showNews(it) }
                }


            })
    }






}




