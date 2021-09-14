package com.example.home.presenter


import com.example.common.base.BaseBean
import com.example.common.base.BaseContract
import com.example.common.base.BasePresenter
import com.example.common.bean.BannerBean
import com.example.common.bean.DataX
import com.example.common.bean.HotKeyBean
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

    override fun loadMore() {//加载更多
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

    override fun search(page:Int,word:String,isRefresh:Boolean) {
        model.serach(page,word).subscribe(object :
            BaseResourceObserver<BaseBean<NewsBean<List<DataX>>>>() {
            override fun onSubscribe(d: Disposable) {

            }


            override fun onNext(t: BaseBean<NewsBean<List<DataX>>>) {

                if (mView is HomeContract.SearchDetailView) {
                    (mView as HomeContract.SearchDetailView).getSearchData(t.data!!.pageCount,t.data,isRefresh)
                }
//              mView?.showNews(t.data)
//              t.data?.let { mView?.showNews(it) }
            }


        })
    }

    override fun removeArticle(position: Int, data: DataX) {
        model.removeArticle(data.id).subscribe(object :
            BaseResourceObserver<BaseBean<NewsBean<List<DataX>>>>(){
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: BaseBean<NewsBean<List<DataX>>>) {
            data.collect=false
                if (mView is HomeContract.HomeView) {
                   data?.let { (mView as HomeContract.HomeView).removeArticleSuccess(position,data) }
                }else if (mView is HomeContract.SearchDetailView) {
                    data?.let { (mView as HomeContract.SearchDetailView).removeSuccess(position,data) }
                }

            }

        })
    }

    override fun addArticle(position: Int, data: DataX) {
        model.addArticle(data.id).subscribe(object :
            BaseResourceObserver<BaseBean<NewsBean<List<DataX>>>>(){
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: BaseBean<NewsBean<List<DataX>>>) {

                if (mView is HomeContract.HomeView) {
                    data.collect=true
                    (mView as HomeContract.HomeView).addArticleSuccess(position,data)
                }else if (mView is HomeContract.SearchDetailView){
                    data.collect=true
                    (mView as HomeContract.SearchDetailView).addSuccess(position,data)
                }

            }

        })
    }

    override fun hotKey() {
        model.hotKey().subscribe(object :BaseResourceObserver<BaseBean<List<HotKeyBean>>>(){
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: BaseBean<List<HotKeyBean>>) {
                if (mView is HomeContract.SearchView) {
                    t.data?.let { (mView as HomeContract.SearchView).getHotKey(it) }
                }
            }

        })
    }

}




