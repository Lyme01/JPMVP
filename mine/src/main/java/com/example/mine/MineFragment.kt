package com.example.mine


import android.view.*

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseFragment
import com.example.common.bean.UserInfoBean
import com.example.common.util.Constant
import com.example.common.util.SpfUtils
import com.example.mine.adapter.ItemListAdapter
import com.example.mine.bean.MineBean
import com.example.mine.contract.MineContract
import com.example.mine.databinding.FragmentMineBinding
import com.example.mine.dialog.CustomDialog
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
class MineFragment : BaseFragment<FragmentMineBinding, MinePresenter>(),View.OnClickListener,MineContract.MineView,OnItemClickListener{

    private var itemListAdapter: ItemListAdapter?=null
    private var datas= mutableListOf<MineBean>()
    private var isLogin=isLogin()

    private val RESID = intArrayOf(
        R.mipmap.rank,
        R.mipmap.ic_share_article,
        R.mipmap.ic_collect,
        R.mipmap.ic_about,
        R.mipmap.ic_setting
    )
    private val TEXT = arrayOf(
        "积分排行榜", "我的分享", "我的收藏", "关于软件", "设置"
    )

    override fun initView() {
        binding.mineRecyclerview.layoutManager=LinearLayoutManager(context)
      itemListAdapter= ItemListAdapter(R.layout.item_mine,datas)
        binding.mineRecyclerview.adapter=itemListAdapter
        itemListAdapter!!.setOnItemClickListener(this)
        binding.tvPerson.setOnClickListener(this)
        binding.logout.setOnClickListener(this)
        if (isLogin){
            binding.logout.visibility=View.VISIBLE
        }else{
            binding.logout.visibility=View.GONE
        }
    }



    override fun initDatas() {
        for (i in TEXT.indices) {
            val bean = MineBean()
            bean.resId = RESID[i]
            bean.text = TEXT[i]
            datas.add(bean)
        }
        itemListAdapter?.notifyDataSetChanged()
       mPresenter?.getUserInfo()
    }

    override fun getPresenter(): MinePresenter {
     return  MinePresenter()
    }

    override fun onClick(v: View?) {
         when(v){
             binding.tvPerson-> {
                 if (!isLogin){
                     ARouter.getInstance().build(RouterUrl.Login.Login).navigation()
                 }
             }
             binding.logout->{
//                 mPresenter?.logout()
                 initDialog()
             }
         }

    }

    override fun getUserInfo(userInfo: UserInfoBean) {
        if (isLogin){
            binding.mineLevel.text= userInfo.coinInfo.level.toString()
            binding.mineRank.text=userInfo.coinInfo.rank
            binding.mineCoin.text=userInfo.coinInfo.coinCount.toString()
            binding.tvPerson.text=userInfo.coinInfo.username
        }
    }

    override fun logoutSuccess() {
        binding.mineLevel.text= "----"
        binding.mineRank.text="----"
        binding.mineCoin.text="----"
        binding.tvPerson.text="未登录"
        isLogin()
        binding.logout.visibility=View.GONE

    }


    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
      when(position){
          1->{
              Toast.makeText(context,"666",LENGTH_SHORT).show()
          }
          4->{
              ARouter.getInstance().build(RouterUrl.Mine.Setting).navigation()

          }

      }
    }

    private fun isLogin(): Boolean {
        return SpfUtils.get(Constant.KEY_IS_LOGIN, false);
    }

    private fun initDialog(){

        var dialog= context?.let { CustomDialog(it) }
        val window: Window = dialog?.window!!
        if (dialog != null && window != null) {
            val attr: WindowManager.LayoutParams = window.attributes
            if (attr != null) {
                attr.height = ViewGroup.LayoutParams.WRAP_CONTENT
                attr.width = ViewGroup.LayoutParams.WRAP_CONTENT
                attr.gravity = Gravity.CENTER //设置dialog 在布局中的位置
            }
        }
        dialog?.let {
            it.setMessage("确定退出登录?")
            it.setSingle(false)?.setOnClickBottomListener(object :CustomDialog.OnClickBottomListener{
                override fun onPositiveClick() {
                    mPresenter?.logout()
                    dialog.dismiss()

                }

                override fun onNegtiveClick() {
                    dialog.dismiss()
                    Toast.makeText(context,"888", LENGTH_SHORT).show()
                }

            }

            )?.show()
        }




    }




}

