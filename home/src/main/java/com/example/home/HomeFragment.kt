package com.example.home

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.base.BaseFragment
import com.example.home.adapter.HomeAdapter
import com.example.common.bean.NewsBean
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

//    override fun testView(list: List<DataBean>) {
//        setSimpleAdapter(list)
//    }
//
//    override fun testTab(tabBean: TabBean) {
//       binding.text.text=tabBean.datas[2].title
//    }

    private fun setSimpleAdapter(listData: NewsBean) {
        var homeAdapter = HomeAdapter(listData,this)
        binding.recycleView?.adapter = homeAdapter
        //LayoutManger必须设置，否则不显示列表
        binding.recycleView?.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recycleView.addItemDecoration(dividerItemDecoration);
    }

    override fun showNews(newsBean: NewsBean) {
        setSimpleAdapter(newsBean)
    }


}