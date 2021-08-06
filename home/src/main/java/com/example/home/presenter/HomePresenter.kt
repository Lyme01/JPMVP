package com.example.home.presenter


import com.example.common.base.BaseBean
import com.example.common.base.BaseContract
import com.example.common.base.BasePresenter
import com.example.common.bean.BannerBean
import com.example.common.bean.DataX
import com.example.common.bean.NewsBean
import com.example.common.http.BaseResourceObserver
import com.example.common.model.HomeModel
import com.example.home.contract.HomeContract
import io.reactivex.disposables.Disposable

class HomePresenter : BasePresenter<BaseContract.BaseView>(),
    HomeContract.Presenter {
    private var mCurNum = 0
    var model=HomeModel()
    override fun getNews() {
      model.getNews(mCurNum).subscribe(object :
          BaseResourceObserver<BaseBean<NewsBean<List<DataX>>>>() {
          override fun onSubscribe(d: Disposable) {

          }


          override fun onNext(t: BaseBean<NewsBean<List<DataX>>>) {

              if (mView is HomeContract.HomeView) {
                  (mView as HomeContract.HomeView).showNews(t.data)
              }
//              mView?.showNews(t.data)
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
                if (mView is HomeContract.HomeView) {
                    (mView as HomeContract.HomeView).loadArticle(t.data)
                }

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
                if (mView is HomeContract.HomeView) {
                    (mView as HomeContract.HomeView).reloadArticle(t.data)
                }

            }


        })
    }

    override fun getBanner() {
        model.getBanner().subscribe(object :
            BaseResourceObserver<BaseBean<List<BannerBean>>>() {
            override fun onSubscribe(d: Disposable) {

            }
            override fun onNext(t: BaseBean<List<BannerBean>>) {
                if (mView is HomeContract.HomeView) {
                    t.data?.let { (mView as HomeContract.HomeView).showBanner(it) }
                }

            }
        })
    }

    override fun search(word:String) {
        model.serach(mCurNum,word).subscribe(object :
            BaseResourceObserver<BaseBean<NewsBean<List<DataX>>>>() {
            override fun onSubscribe(d: Disposable) {

            }


            override fun onNext(t: BaseBean<NewsBean<List<DataX>>>) {

                if (mView is HomeContract.SearchView) {
                    (mView as HomeContract.SearchView).getSearch(t.data)
                }
//              mView?.showNews(t.data)
//              t.data?.let { mView?.showNews(it) }
            }


        })
    }


}




