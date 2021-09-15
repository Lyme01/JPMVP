package com.example.mine.presenter

import com.example.common.base.BaseBean
import com.example.common.base.BaseContract
import com.example.common.base.BasePresenter
import com.example.common.bean.DataX
import com.example.common.bean.NewsBean
import com.example.common.bean.UserBean
import com.example.common.bean.UserInfoBean
import com.example.common.http.Api
import com.example.common.http.BaseResourceObserver
import com.example.mine.contract.MineContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MinePresenter:BasePresenter<BaseContract.BaseView>(),MineContract.MinePresenter {

    override fun logout() {
        Api.getInstance().getApiService().logout().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(object : BaseResourceObserver<BaseBean<UserBean>>() {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: BaseBean<UserBean>) {
                setLogin(false)
                setUserName("")
                setPassword("")
             if (mView is MineContract.MineView){
                 (mView as MineContract.MineView)?.logoutSuccess()
             }

            }

        })
    }

    override fun getUserInfo() {
        Api.getInstance().getApiService().getUserInfo().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(object : BaseResourceObserver<BaseBean<UserInfoBean>>() {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: BaseBean<UserInfoBean>) {
                if (mView is MineContract.MineView){
                    t.data?.let { (mView as MineContract.MineView)?.getUserInfo(it) }
                }
            }

        })
        }

    override fun getCollect(page:Int,isRefresh:Boolean) {
        Api.getInstance().getApiService().getCollect(page).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(object : BaseResourceObserver<NewsBean<List<DataX>>>(){
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: NewsBean<List<DataX>>) {
              if (mView is MineContract.CollectView){
                  t?.let { (mView as MineContract.CollectView)?.getCollectArticles(it.pageCount,it,isRefresh) }
              }
            }

        })
    }

}
