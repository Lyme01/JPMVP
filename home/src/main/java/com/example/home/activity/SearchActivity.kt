package com.example.home.activity



import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.arouter.RouterUrl
import com.example.common.base.BaseActivity
import com.example.common.bean.HotKeyBean
import com.example.home.adapter.LabelAdaper
import com.example.home.adapter.NewsAdapter
import com.example.home.contract.HomeContract
import com.example.home.databinding.ActivitySearchBinding
import com.example.home.presenter.HomePresenter
import com.tbruyelle.rxpermissions2.RxPermissions
import android.Manifest.permission
import android.util.Log
import com.example.home.R



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

//        rxPermissions.request( //添加需要的权限
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//        ).subscribe(object : Observer<Boolean?> {
//            //订阅
//
//            override fun onNext(aBoolean: Boolean) {
//                if (aBoolean) { //
//                    Toast.makeText(this@SearchActivity, "允许了权限", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this@SearchActivity, "未授权权限", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//
//            override fun onSubscribe(d: Disposable) {
//
//            }
//
//            override fun onError(e: Throwable) {
//
//            }
//
//            override fun onComplete() {
//
//            }
//        })

//        RxPermissions(this)
//            .request(permission.CAMERA, permission.READ_PHONE_STATE)
//            .subscribe { granted: Boolean ->
//                if (granted) {
//                    Log.e("TAG", "所有权限都已打开！")
//                } else {
//                    Log.e("TAG", "至少有一个权限被禁止！")
//                }
//            }
        RxPermissions(this)
            .requestEach(permission.CAMERA, permission.READ_PHONE_STATE)
            .subscribe { permission ->
                if (permission.granted) {
                    Log.e("TAG", permission.name.toString() + "权限被授予！")
                } else if (permission.shouldShowRequestPermissionRationale) {
                    Log.e("TAG", permission.shouldShowRequestPermissionRationale.toString() + "")
                } else {
                    Log.e("TAG", permission.name.toString() + "权限被禁止！")
                }
            }

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








