package com.example.home

import android.content.Context
import android.provider.ContactsContract
import android.view.View
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
class HomeFragment : BaseFragment<FragmentHomeBinding, HomePresenter>(), HomeContract.View , View.OnClickListener {

   lateinit var homeAdapter: HomeAdapter
   var nickname:String?=null


    interface SendListener{
        fun getNickname(nickname: String)
    }

    private val listData: List<NewsBean> = ArrayList<NewsBean>()
   override fun initView() {
//        mPresenter?.testPresenter()
//        mPresenter?.getTab()
       mPresenter?.getNews()
       binding.login.setOnClickListener(this)

    }

    override fun getPresenter(): HomePresenter {
     return HomePresenter()
    }


    private fun setSimpleAdapter(listData: NewsBean) {
        homeAdapter = HomeAdapter(listData)
        binding.recycleView?.adapter = homeAdapter
        //LayoutManger必须设置，否则不显示列表
        binding.recycleView?.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recycleView.addItemDecoration(dividerItemDecoration);
//        homeAdapter.notifyDataSetChanged();
        homeAdapter!!.setOnItemClickListener(object : HomeAdapter.ItemClickListener {
            override fun onItemClickListener(position: Int) {
                ARouter.getInstance().build(RouterUrl.Web.H5).withString(
                    ActionString.H5URL,
                    listData.datas[position].link
                ).navigation()
            }
        })

    }

    override fun showNews(newsBean: NewsBean) {
        setSimpleAdapter(newsBean)
    }

    override fun onClick(v: View?) {

        ARouter.getInstance().build(RouterUrl.Login.Login).navigation()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

}