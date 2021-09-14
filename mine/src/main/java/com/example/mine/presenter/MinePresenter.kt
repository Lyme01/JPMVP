package com.example.mine.presenter

import com.example.common.base.BaseBean
import com.example.common.base.BaseContract
import com.example.common.base.BasePresenter
import com.example.common.bean.UserBean
import com.example.common.bean.UserInfoBean
import com.example.common.http.Api
import com.example.common.http.BaseResourceObserver
import com.example.mine.contract.MineContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MinePresenter:BasePresenter<MineContract.MineView>(),MineContract.MinePresenter {

    override fun logout() {
        Api.getInstance().getApiService().logout().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(object : BaseResourceObserver<BaseBean<UserBean>>() {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: BaseBean<UserBean>) {
                setLogin(false)
                setUserName("")
                setPassword("")
                mView?.logoutSuccess()

            }

        })
    }

    override fun getUserInfo() {
        Api.getInstance().getApiService().getUserInfo().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(object : BaseResourceObserver<BaseBean<UserInfoBean>>() {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: BaseBean<UserInfoBean>) {
                t.data?.let { mView?.getUserInfo(it) }
            }

        })
        }

    }
