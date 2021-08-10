package com.example.mine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseFragment
import com.example.mine.databinding.FragmentMineBinding
import com.example.mine.presenter.MinePresenter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MineFragment : BaseFragment<FragmentMineBinding, MinePresenter>(),View.OnClickListener{
    override fun initView() {
      binding.login.setOnClickListener(this)
    }

    override fun initDatas() {

    }

    override fun getPresenter(): MinePresenter {
     return  MinePresenter()
    }

    override fun onClick(v: View?) {
        when(v){
            binding.login->{
             mPresenter?.logout()
            }
        }
    }

}