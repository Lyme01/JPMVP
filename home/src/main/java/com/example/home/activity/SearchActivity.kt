package com.example.home.activity


import android.opengl.Visibility
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseActivity
import com.example.common.bean.DataX
import com.example.common.bean.HotKeyBean
import com.example.common.bean.NewsBean
import com.example.home.R
import com.example.home.adapter.LabelAdaper
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
    private var mLabelAdaper: LabelAdaper? = null
    private val mDatas = mutableListOf<HotKeyBean>()
    override fun getPresenter(): HomePresenter {
       return  HomePresenter()
    }

    override fun initView() {
        binding.titleRightIco.setOnClickListener(this)
        mLabelAdaper = LabelAdaper(R.layout.item_textview, mDatas)
        binding.labelflow?.setAdapter(mLabelAdaper)

    }

    override fun setEvents() {

    }

    override fun initDatas() {
        mPresenter?.hotKey()
    }





    override fun getHotKey(hotKeys: List<HotKeyBean>) {
        mDatas.clear()
        mDatas.addAll(hotKeys)
        mLabelAdaper!!.notifyDataChanged()
    }

    override fun onClick(v: View?) {
       when(v){
           binding.titleRightIco -> {
               if (binding.etSearch.text.isNotEmpty()) {
//                   mPresenter?.search(binding.etSearch.text.toString())
                   var word=binding.etSearch.text.toString()
                   ARouter.getInstance().build(RouterUrl.Home.Detail).withString("word",word).navigation()
               } else {
                   Toast.makeText(this, "请输入搜索词", Toast.LENGTH_SHORT).show()
               }
           }
       }
    }
}








