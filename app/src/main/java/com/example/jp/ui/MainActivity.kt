package com.example.jp.ui


import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.adapter.CommonFragmentPagerAdapter
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseActivity
import com.example.common.view.BottomNavView
import com.example.find.FindFragment
import com.example.home.HomeFragment
import com.example.jp.R
import com.example.jp.contract.MainContract
import com.example.jp.databinding.ActivityMainBinding
import com.example.jp.presenter.MainPresenter
import com.example.mine.MineFragment
import java.util.*

 @Route(path = RouterUrl.APP.Tab)
class MainActivity : BaseActivity<ActivityMainBinding, MainPresenter>(), MainContract.View ,
    BottomNavView.OnBottomViewItemSelectedListener {

    private var fragmentList: List<Fragment>? = null
    private var mTitleList: Array<String>?=null
    private var mSelectedIcon: IntArray?=null
    private var mUnSelectedIcon: IntArray?=null
     var nickname:String?=null
    override fun getPresenter(): MainPresenter {
        return MainPresenter()
    }


    override fun initView() {
       nickname=intent.getStringExtra("nickname")
    }




     protected override fun setEvents() {
       binding.mViewPager.addOnPageChangeListener(object : OnPageChangeListener {
           override fun onPageScrolled(
               position: Int,
               positionOffset: Float,
               positionOffsetPixels: Int
           ) {
           }

           override fun onPageSelected(position: Int) {
               binding.bottomNav.setCurrentItem(position)
           }

           override fun onPageScrollStateChanged(state: Int) {}
       })

    }

    override fun initDatas() {
        fragmentList = ArrayList()
        mTitleList = arrayOf("首页", "发现", "我的")
        val also = intArrayOf(R.mipmap.home_icon, R.mipmap.contacts_icon, R.mipmap.my_icon).also { mUnSelectedIcon = it }
        mSelectedIcon = intArrayOf(
            R.mipmap.home_icon_1,
            R.mipmap.contacts_icon_1,
            R.mipmap.my_icon_1
        )

        (fragmentList as ArrayList<Fragment>)?.add(HomeFragment())
        (fragmentList as ArrayList<Fragment>)?.add(FindFragment())
        (fragmentList as ArrayList<Fragment>)?.add(MineFragment())

        binding.mViewPager.adapter = CommonFragmentPagerAdapter(
            supportFragmentManager,
            fragmentList
        )

        binding.bottomNav
                .setItemViewResId(R.layout.menu_main_activity)
                .setmTextList(mTitleList)
                .setSelectedTextColor(resources.getColor(R.color.common_blue))
                .setNormalTextColor(resources.getColor(R.color.common_gray))
                .setNormalIconArray(mUnSelectedIcon)
                .setSelectedIconArray(mSelectedIcon)
                .setListener(this)
                .build() }

    override fun onItemSelected(view: View?, position: Int) {
        binding.bottomNav.setCurrentItem(position)
        binding.mViewPager.setCurrentItem(position, false)
    }


 }



