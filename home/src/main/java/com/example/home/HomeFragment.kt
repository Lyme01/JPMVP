package com.example.home

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.common.arouter.ActionString
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseFragment
import com.example.common.bean.BannerBean
import com.example.common.bean.DataX
import com.example.common.bean.NewsBean
import com.example.common.util.TitleBuilder
import com.example.home.adapter.NewsAdapter
import com.example.home.contract.HomeContract
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.presenter.HomePresenter
import com.jaeger.library.StatusBarUtil
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader
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
    OnItemChildClickListener, OnItemClickListener, OnBannerListener {

    private var mHomeAdapter: NewsAdapter? = null
//    private var mArticleBeans= mutableListOf<DataX>()
    private var images= mutableListOf<String>()
    private var titles= mutableListOf<String>()
    private var urls= mutableListOf<String>()


   override fun initView() {

//       binding.login.setOnClickListener(this)
        //LayoutManger必须设置，否则不显示列表
        binding.recycleView?.layoutManager = LinearLayoutManager(context)
       mHomeAdapter = NewsAdapter(R.layout.item_home, null)
       binding.recycleView?.adapter =mHomeAdapter
      mHomeAdapter!!.setOnItemChildClickListener(this)
      mHomeAdapter!!.setOnItemClickListener(this)
//    val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
//    binding.recycleView.addItemDecoration(dividerItemDecoration);
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
    override fun initDatas() {
        mPresenter?.getBanner()
        mPresenter?.getNews()
    }


    override fun getPresenter(): HomePresenter {
     return HomePresenter()
    }



    override fun onClick(v: View?) {
        ARouter.getInstance().build(RouterUrl.Login.Login).navigation()
    }


    override fun showNews(articles: NewsBean<List<DataX>>?) {
//        mArticleBeans.clear()
//        articles?.let { mArticleBeans.addAll(it.datas) }
        mHomeAdapter?.setNewInstance(articles?.datas as MutableList<DataX>?)
    }

    override fun loadArticle(articles: NewsBean<List<DataX>>?) {
        if (articles != null) {
//            articles?.let { mArticleBeans.addAll(it.datas) }
            mHomeAdapter?.addData(articles?.datas)
            mHomeAdapter?.notifyDataSetChanged()
        }
    }

    override fun reloadArticle(articles: NewsBean<List<DataX>>?) {
//        mArticleBeans.clear()
        if (articles != null) {
//            articles?.let { mArticleBeans.addAll(it.datas) }
            mHomeAdapter?.setNewInstance(articles?.datas as MutableList<DataX>?)
            mHomeAdapter?.notifyDataSetChanged()
        }
    }

    override fun showBanner(banners: List<BannerBean>) {
        for (i in banners.indices){
            images.add(banners[i].imagePath)
            titles.add(banners[i].title)
            urls.add(banners[i].url)
        }
        binding.mBanner.setImageLoader(MyLoader())
        binding.mBanner.let {
            it.setBannerAnimation(Transformer.Accordion);
            it.setImages(images);//设置图片资源
            it.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//设置banner显示样式（带标题的样式）
            it.setBannerTitles(titles); //设置标题集合（当banner样式有显示title时）
            it.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置（即图片下面的那个小圆点）
            it.setDelayTime(1000);//设置轮播时间3秒切换下一图
            it.setOnBannerListener(this);//设置监听
            it.start();//开始进行banner渲染
        }

    }



    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
//       var url=mArticleBeans[position].link
        var url =mHomeAdapter?.data?.get(position)?.link
        ARouter.getInstance().build(RouterUrl.Web.H5).withString(ActionString.H5URL, url).navigation()
    }

    //自定义的图片加载器
    private class MyLoader : ImageLoader() {
        override fun displayImage(context: Context, path: Any, imageView: ImageView) {
            Glide.with(context).load(path).into(imageView)
        }
    }

    override fun OnBannerClick(position: Int) {
        var url=urls[position]
        ARouter.getInstance().build(RouterUrl.Web.H5).withString(ActionString.H5URL, url).navigation()
    }


}


