package com.example.jp.presenter


import com.example.common.base.BasePresenter
import com.example.jp.contract.MainContract



class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {
//    override fun testPresenter() {
//        Api.getInstance().getApiService()
//            .getData()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe (object : BaseResourceObserver<BaseBean<List<DataBean>>>() {
//                override fun onSubscribe(d: Disposable) {
//                    Log.e("hhhhhh","kkkkkkk")
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




//    override fun getTab(){
//        Api.getInstance().getApiService()
//                .getTab()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe (object : BaseResourceObserver<String>() {
//                    override fun onSubscribe(d: Disposable) {
//
//                    }
//
//                    override fun onNext(t: String) {
//                        mView!!.testTab(t)
//                    }
//                })
    }


