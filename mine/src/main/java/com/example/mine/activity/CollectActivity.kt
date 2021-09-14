package com.example.mine.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.base.BaseActivity
import com.example.common.bean.DataX
import com.example.mine.R
import com.example.mine.adapter.CollectAdapter
import com.example.mine.contract.MineContract
import com.example.mine.databinding.ActivityCollectBinding
import com.example.mine.presenter.MinePresenter

class CollectActivity :BaseActivity<ActivityCollectBinding,MinePresenter>(),MineContract.CollectView {

    private var mCollectAdapter: CollectAdapter? = null
    override fun getPresenter(): MinePresenter {
     return MinePresenter()
    }

    override fun initView() {
        binding.recycleView.layoutManager=LinearLayoutManager(this)
        mCollectAdapter=
            CollectAdapter(R.layout.item_home, null)
        binding.recycleView.adapter=mCollectAdapter
    }

    override fun setEvents() {

    }

    override fun initDatas() {

    }

    override fun getCollectArticles(data: DataX) {

    }

}