package com.example.home.activity


import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseActivity
import com.example.common.bean.DataX
import com.example.common.bean.NewsBean
import com.example.home.R
import com.example.home.adapter.NewsAdapter
import com.example.home.contract.HomeContract
import com.example.home.databinding.ActivitySearchBinding
import com.example.home.presenter.HomePresenter
import com.jaeger.library.StatusBarUtil
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

@Route(path = RouterUrl.Home.Search)
class SearchActivity : BaseActivity<ActivitySearchBinding, HomePresenter>(),HomeContract.SearchView,
    View.OnClickListener {
    private var mHomeAdapter: NewsAdapter? = null

    override fun getPresenter(): HomePresenter {
       return  HomePresenter()
    }

    override fun initView() {
        binding.titleRightIco.setOnClickListener(this)
        StatusBarUtil.setColor(this, getResources().getColor(com.example.common.R.color.c_1B77A8),0)
        binding.recycleView?.layoutManager = LinearLayoutManager(this)
        mHomeAdapter = NewsAdapter(R.layout.item_home, null)
        binding.recycleView?.adapter =mHomeAdapter
        binding.refresh.setRefreshHeader(BezierRadarHeader(this).setEnableHorizontalDrag(true))
        binding.refresh.setRefreshFooter(BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale))
        binding.refresh.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshlayout: RefreshLayout) {

                refreshlayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败
            }
        })

        binding.refresh.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore(refreshlayout: RefreshLayout) {

                refreshlayout.finishLoadMore(2000 /*,false*/) //传入false表示加载失败
            }
        })
    }

    override fun setEvents() {

    }

    override fun initDatas() {
    }

    override fun getSearch(articles: NewsBean<List<DataX>>?) {
        mHomeAdapter?.setNewInstance(articles?.datas as MutableList<DataX>?)
    }

    override fun onClick(v: View?) {
       when(v){
           binding.titleRightIco->{
               if (binding.etSearch.text.isNotEmpty()) {
                   mPresenter?.search(binding.etSearch.text.toString())
               }else{
                   Toast.makeText(this,"请输入搜索词",Toast.LENGTH_SHORT).show()
               }
           }
       }
    }
}






