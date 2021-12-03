package com.example.find.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.find.R


/**
 * @author wwq
 * @description:
 * @date :2021/12/3
 */

class ViewAdapter: BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_page){


    override fun convert(holder: BaseViewHolder, item:String) {
        holder.setText(R.id.tv,item)
    }
}