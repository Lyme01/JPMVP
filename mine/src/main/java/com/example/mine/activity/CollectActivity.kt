package com.example.mine.activity

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.common.arouter.ActionString
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseActivity
import com.example.common.bean.DataX
import com.example.common.bean.NewsBean
import com.example.common.bean.WebBean
import com.example.common.util.Constant
import com.example.common.util.SpfUtils
import com.example.common.util.TitleBuilder
import com.example.mine.R
import com.example.mine.adapter.CollectAdapter
import com.example.mine.contract.MineContract
import com.example.mine.databinding.ActivityCollectBinding
import com.example.mine.presenter.MinePresenter
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
@Route(path = RouterUrl.Mine.Collect)
class CollectActivity :BaseActivity<ActivityCollectBinding,MinePresenter>(),MineContract.CollectView,
    OnItemClickListener, OnItemChildClickListener {

    private var mCollectAdapter: CollectAdapter? = null
    private var page:Int=0
    private var mMaxPage:Int? = null
    private var  mArticleDatas= mutableListOf<DataX>()

    override fun getPresenter(): MinePresenter {
     return MinePresenter()
    }

    override fun initView() {
        TitleBuilder(this).setTitleText("我的收藏").setLeftIco(R.mipmap.return_icon_1).setLeftIcoListening(
            leftReturnListener
        )
        binding.recycleView.layoutManager=LinearLayoutManager(this)
        mCollectAdapter=
            CollectAdapter(R.layout.item_home, mArticleDatas)
        binding.recycleView.adapter=mCollectAdapter
        mCollectAdapter!!.setOnItemChildClickListener(this)
        mCollectAdapter!!.setOnItemClickListener(this)
        binding.recycleView?.adapter =mCollectAdapter
        binding.refresh.setRefreshHeader(BezierRadarHeader(this).setEnableHorizontalDrag(true))
        binding.refresh.setRefreshFooter(BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale))
        binding.refresh.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshlayout: RefreshLayout) {

                refreshlayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败
            }
        })

        binding.refresh.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore(refreshlayout: RefreshLayout) {
                if (page >= mMaxPage!!) {
                    Toast.makeText(applicationContext, "没有更多文章了", Toast.LENGTH_SHORT).show()
                } else {
                    page++
                    mPresenter?.getCollect(page,false)
                    mCollectAdapter?.notifyDataSetChanged()
                }
                refreshlayout.finishLoadMore(2000 /*,false*/) //传入false表示加载失败
            }
        })
    }

    override fun setEvents() {

    }

    override fun initDatas() {
       mPresenter?.getCollect(page,true)
    }



    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        var webBean= WebBean()
        webBean.id= mCollectAdapter?.data?.get(position)?.id!!
        webBean.isCollect=mCollectAdapter?.data?.get(position)?.collect!!
        webBean.position=position
        webBean.url=mCollectAdapter?.data?.get(position)?.link!!
        webBean.title=mCollectAdapter?.data?.get(position)?.title
        ARouter.getInstance().build(RouterUrl.Web.H5).withSerializable(ActionString.H5URL, webBean).navigation()
    }



    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }

    override fun getCollectArticles(maxPage:Int,data: NewsBean<List<DataX>>,isRefresh: Boolean) {
        mMaxPage = maxPage
        if (isRefresh) {
            mArticleDatas.clear()
        }
     mArticleDatas.addAll(data!!.datas)
     mCollectAdapter?.notifyDataSetChanged()
    }

    private var leftReturnListener=View.OnClickListener { finish() }




}