package com.example.home.presenter


import com.example.common.base.BasePresenter
import com.example.common.base.BaseBean
import com.example.common.http.Api


import com.example.common.http.BaseResourceObserver
import com.example.common.bean.NewsBean
import com.example.home.contract.HomeContract

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomePresenter : BasePresenter<HomeContract.View>(),
    HomeContract.Presenter {
    override fun getNews() {
        Api.getInstance().getApiService()
            .getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseResourceObserver<BaseBean<NewsBean>>() {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseBean<NewsBean>) {
                    mView?.showNews(t.result!!)
                }
            })
    }
//    override fun testPresenter() {
//        Api.getInstance().getApiService()
//            .getData()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : BaseResourceObserver<BaseBean<List<DataBean>>>() {
//                override fun onSubscribe(d: Disposable) {
//                    Log.e("hhhhhh", "kkkkkkk")
//                }
//
//
//                override fun onNext(t: BaseBean<List<DataBean>>) {
//                    t.data?.let { mView?.testView(it) }
//                }
//
//
//            })
//    }






}




