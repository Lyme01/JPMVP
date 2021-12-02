package com.example.mine.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mine.R
import com.example.mine.bean.MineBean

 class ItemListAdapter() : BaseQuickAdapter<MineBean, BaseViewHolder>(
     R.layout.item_mine
) {
    override fun convert(holder: BaseViewHolder, item: MineBean) {
        val imageView: ImageView = holder.getView(R.id.item_mine_iv)
        imageView.setColorFilter(R.color.main_color)
        holder.setImageResource(R.id.item_mine_iv, item.resId)
            .setText(R.id.item_mine_tv, item.text)
    }
}