package com.example.home

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.common.arouter.ActionString
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseFragment
import com.example.common.bean.DataX
import com.example.common.bean.NewsBean
import com.example.home.adapter.NewsAdapter
import com.example.home.contract.HomeContract
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.presenter.HomePresenter
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomePresenter>(), HomeContract.View ,View.OnClickListener,
    OnItemChildClickListener, OnItemClickListener {

    private var mHomeAdapter: NewsAdapter? = null
    private var mArticleBeans= mutableListOf<DataX>()


   override fun initView() {
       mPresenter?.getNews()
       binding.login.setOnClickListener(this)
        //LayoutManger必须设置，否则不显示列表
        binding.recycleView?.layoutManager = LinearLayoutManager(context)
       mHomeAdapter = NewsAdapter(R.layout.item_home, null)
       binding.recycleView?.adapter =mHomeAdapter
      mHomeAdapter!!.setOnItemChildClickListener(this)
      mHomeAdapter!!.setOnItemClickListener(this)
    val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    binding.recycleView.addItemDecoration(dividerItemDecoration);
    binding.refresh.setRefreshHeader(BezierRadarHeader(context).setEnableHorizontalDrag(true))
    binding.refresh.setRefreshFooter(BallPulseFooter(context).setSpinnerStyle(SpinnerStyle.Scale))
    binding.refresh.setOnRefreshListener(object : OnRefreshListener {
        override fun onRefresh(refreshlayout: RefreshLayout) {
            mPresenter?.reload()
            refreshlayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败
        }
    })

    binding.refresh.setOnLoadMoreListener(object : OnLoadMoreListener {
        override fun onLoadMore(refreshlayout: RefreshLayout) {
            mPresenter?.loadMore()
            refreshlayout.finishLoadMore(2000 /*,false*/) //传入false表示加载失败
        }
    })
}

    override fun getPresenter(): HomePresenter {
     return HomePresenter()
    }



//    private fun setSimpleAdapter(listData: NewsBean) {
//        homeAdapter = HomeAdapter(listData)
//        binding.recycleView?.adapter = homeAdapter
//        //LayoutManger必须设置，否则不显示列表
//        binding.recycleView?.layoutManager = LinearLayoutManager(context)
//        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
//        binding.recycleView.addItemDecoration(dividerItemDecoration);
////        homeAdapter.notifyDataSetChanged();
//        homeAdapter!!.setOnItemClickListener(object : HomeAdapter.ItemClickListener {
//            override fun onItemClickListener(position: Int) {
//                ARouter.getInstance().build(RouterUrl.Web.H5).withString(
//                    ActionString.H5URL,
//                    listData.datas[position].link
//                ).navigation()
//            }
//        })
//
//    }
//
//    override fun showNews(newsBean: NewsBean) {
//        setSimpleAdapter(newsBean)
//    }

    override fun onClick(v: View?) {
        ARouter.getInstance().build(RouterUrl.Login.Login).navigation()
    }


    override fun showNews(articles: NewsBean<List<DataX>>?) {
        mArticleBeans.clear()
        articles?.let { mArticleBeans.addAll(it.datas) }
        mHomeAdapter?.setNewInstance(mArticleBeans)
    }

    override fun loadArticle(articles: NewsBean<List<DataX>>?) {
        if (articles != null) {
            articles?.let { mArticleBeans.addAll(it.datas) }
            mHomeAdapter?.notifyDataSetChanged()
        }
    }

    override fun reloadArticle(articles: NewsBean<List<DataX>>?) {
        mArticleBeans.clear()
        if (articles != null) {
            articles?.let { mArticleBeans.addAll(it.datas) }
            mHomeAdapter?.notifyDataSetChanged()
        }
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
       var url=mArticleBeans[position].link
        ARouter.getInstance().build(RouterUrl.Web.H5).withString(ActionString.H5URL, url).navigation()
    }


}


