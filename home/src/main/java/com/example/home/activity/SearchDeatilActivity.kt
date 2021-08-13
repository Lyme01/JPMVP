package com.example.home.activity

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.common.arouter.ActionString
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseActivity
import com.example.common.bean.DataX
import com.example.common.bean.NewsBean
import com.example.common.util.Constant
import com.example.common.util.SpfUtils
import com.example.common.util.TitleBuilder
import com.example.home.R
import com.example.home.adapter.NewsAdapter
import com.example.home.contract.HomeContract
import com.example.home.databinding.ActivitySearchDeatilBinding
import com.example.home.presenter.HomePresenter
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

@Route(path = RouterUrl.Home.Detail)
class SearchDeatilActivity : BaseActivity<ActivitySearchDeatilBinding, HomePresenter>(),HomeContract.SearchDetailView,
    OnItemChildClickListener,
    OnItemClickListener {

    private var mHomeAdapter: NewsAdapter? = null
    private var page:Int=0
    private var mMaxPage:Int? = null
    private var hotWord:String?=null
    private var  mArticleDatas= mutableListOf<DataX>()
    override fun getPresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun initView() {


        binding.recycleView?.layoutManager = LinearLayoutManager(this)
        mHomeAdapter = NewsAdapter(R.layout.item_home, mArticleDatas)
        mHomeAdapter!!.setOnItemChildClickListener(this)
        mHomeAdapter!!.setOnItemClickListener(this)
        binding.recycleView?.adapter =mHomeAdapter
        binding.refresh.setRefreshHeader(BezierRadarHeader(this).setEnableHorizontalDrag(true))
        binding.refresh.setRefreshFooter(BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale))
        binding.refresh.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshlayout: RefreshLayout) {
                mPresenter?.search(0, hotWord!!, true)
                refreshlayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败
            }
        })

        binding.refresh.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore(refreshlayout: RefreshLayout) {
                if (page >= mMaxPage!!) {
                    Toast.makeText(applicationContext, "没有更多文章了", Toast.LENGTH_SHORT).show()
                } else {
                    page++
                    mPresenter?.search(page, hotWord!!, false)
                    mHomeAdapter?.notifyDataSetChanged()
                }
                refreshlayout.finishLoadMore(2000 /*,false*/) //传入false表示加载失败
            }
        })
    }

    override fun setEvents() {

    }

    override fun initDatas() {
      var word=intent.getStringExtra("word")
        TitleBuilder(this).setTitleText(word).setTitleColor(R.color.white)
        if (word != null) {
            hotWord=word
            mPresenter?.search(page, word, true)
        }
    }

    override fun getSearch(articles: NewsBean<List<DataX>>?) {

    }

//    override fun addArticleSuccess(position: Int) {
//        Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show()
//    }
//
//
//
//    override fun removeArticleSuccess(position: Int) {
//        Toast.makeText(context, "已取消收藏", Toast.LENGTH_SHORT).show()
//    }


    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        when(view.id){
            R.id.item_article_like -> {
                //是否登录
                val isLogin: Boolean = isLogin()
                if (!isLogin) {
                    ARouter.getInstance().build(RouterUrl.Login.Login).navigation()
                } else {
                    val data: DataX? = mHomeAdapter?.data?.get(position)
                    if (data?.collect!!) {
                        mPresenter?.removeArticle(position, data)
                    } else {
                        mPresenter?.addArticle(position, data)
                    }
                }
            }

        }
    }

    private fun isLogin(): Boolean {
        return SpfUtils.get(Constant.KEY_IS_LOGIN, false);
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
//       var url=mArticleBeans[position].link
        var url =mHomeAdapter?.data?.get(position)?.link
        ARouter.getInstance().build(RouterUrl.Web.H5).withString(ActionString.H5URL, url).navigation()
    }

    override fun addArticleSuccess(position: Int) {
        Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show()
    }

    override fun removeArticleSuccess(position: Int) {
        Toast.makeText(this, "已取消收藏", Toast.LENGTH_SHORT).show()
    }

    override fun getSearchData(maxPage: Int, datas: NewsBean<List<DataX>>?, isRefresh: Boolean) {
        mMaxPage = maxPage
        if (isRefresh) {
            mArticleDatas.clear()
        }
        mArticleDatas.addAll(datas!!.datas)
        mHomeAdapter?.notifyDataSetChanged()
    }


}