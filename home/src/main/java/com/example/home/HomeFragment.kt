package com.example.home

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.arouter.ActionString
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseFragment
import com.example.common.bean.NewsBean
import com.example.home.adapter.HomeAdapter
import com.example.home.contract.HomeContract
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.presenter.HomePresenter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomePresenter>(), HomeContract.View {



   override fun initView() {
//        mPresenter?.testPresenter()
//        mPresenter?.getTab()
       mPresenter?.getNews()

    }

    override fun getPresenter(): HomePresenter {
     return HomePresenter()
    }


    private fun setSimpleAdapter(listData: NewsBean) {
        var homeAdapter = HomeAdapter(listData, this)
        binding.recycleView?.adapter = homeAdapter
        //LayoutManger必须设置，否则不显示列表
        binding.recycleView?.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recycleView.addItemDecoration(dividerItemDecoration);
        homeAdapter!!.setOnItemClickListener(object : HomeAdapter.ItemClickListener {
            override fun onItemClickListener(position: Int) {
               ARouter.getInstance().build(RouterUrl.Web.H5).withString(ActionString.H5URL,listData.datas[position].link).navigation()
            }
        })

    }

    override fun showNews(newsBean: NewsBean) {
        setSimpleAdapter(newsBean)
    }

}