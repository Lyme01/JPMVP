package com.example.home.presenter


import com.example.common.base.BaseBean
import com.example.common.base.BasePresenter
import com.example.common.bean.DataX
import com.example.common.bean.NewsBean
import com.example.common.http.BaseResourceObserver
import com.example.common.model.HomeModel
import com.example.home.contract.HomeContract
import io.reactivex.disposables.Disposable

class HomePresenter : BasePresenter<HomeContract.View>(),
    HomeContract.Presenter {
    private var mCurNum = 0
    var model=HomeModel()
    override fun getNews() {
      model.getNews(mCurNum).subscribe(object :
          BaseResourceObserver<BaseBean<NewsBean<List<DataX>>>>() {
          override fun onSubscribe(d: Disposable) {

          }


          override fun onNext(t: BaseBean<NewsBean<List<DataX>>>) {
              mView?.showNews(t.data)
//              t.data?.let { mView?.showNews(it) }
          }


      })
    }

    override fun loadMore() {
        mCurNum++
        model.getNews(mCurNum).subscribe(object :
            BaseResourceObserver<BaseBean<NewsBean<List<DataX>>>>() {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: BaseBean<NewsBean<List<DataX>>>) {
                mView?.loadArticle(t.data)
            }


        })
    }

    override fun reload() {
        mCurNum=0
        model.getNews(mCurNum).subscribe(object :
            BaseResourceObserver<BaseBean<NewsBean<List<DataX>>>>() {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: BaseBean<NewsBean<List<DataX>>>) {
             mView?.reloadArticle(t.data)
            }


        })
    }






}




