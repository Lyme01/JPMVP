package com.example.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.base.BaseFragment
import com.example.find.adapter.ViewAdapter
import com.example.find.databinding.FragmentFindBinding
import com.example.find.presenter.FindPresenter
import androidx.recyclerview.widget.StaggeredGridLayoutManager






class FindFragment : BaseFragment<FragmentFindBinding,FindPresenter>(){
    private val adapter by lazy { ViewAdapter() }
    override fun initView() {
      binding.recycle.adapter=adapter
      binding.recycle.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)

    }

    override fun initDatas() {
        val numbers = mutableListOf("one", "five", "six","one", "five", "six","one", "five", "six","one", "five", "six")
        adapter.addData(numbers)
    }

    override fun getPresenter(): FindPresenter {
       return FindPresenter()
    }

}