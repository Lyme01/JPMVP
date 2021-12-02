package com.example.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.base.BaseFragment
import com.example.find.databinding.FragmentFindBinding
import com.example.find.presenter.FindPresenter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FindFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FindFragment : BaseFragment<FragmentFindBinding,FindPresenter>(){
    override fun initView() {

    }

    override fun initDatas() {

    }

    override fun getPresenter(): FindPresenter {
       return FindPresenter()
    }

}