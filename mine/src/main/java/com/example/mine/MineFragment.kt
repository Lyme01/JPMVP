package com.example.mine


import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.listener.GridSpanSizeLookup
import com.example.common.base.BaseFragment
import com.example.common.bean.UserInfoBean
import com.example.mine.adapter.MultipleItemQuickAdapter
import com.example.mine.bean.MultipleItem
import com.example.mine.contract.MineContract
import com.example.mine.databinding.FragmentMineBinding
import com.example.mine.presenter.MinePresenter
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MineFragment : BaseFragment<FragmentMineBinding, MinePresenter>(),View.OnClickListener,MineContract.MineView{


    override fun initView() {

    }















    override fun initDatas() {
       mPresenter?.getUserInfo()
    }

    override fun getPresenter(): MinePresenter {
     return  MinePresenter()
    }

    override fun onClick(v: View?) {

    }

    override fun getUserInfo(userInfo: UserInfoBean) {



    }


}