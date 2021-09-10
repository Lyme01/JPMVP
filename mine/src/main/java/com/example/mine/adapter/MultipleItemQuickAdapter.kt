package com.example.mine.adapter

import android.view.View
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mine.R
import com.example.mine.bean.MultipleItem
import java.lang.String

class MultipleItemQuickAdapter(data: List<MultipleItem>?) :BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder>(
    data as MutableList<MultipleItem>?
){
    init {
//        addItemType(MultipleItem.TYPE_COUNT, R.layout.layout_my_count)
//        addItemType(MultipleItem.TYPE_ORDER_HEADER, R.layout.layout_my_order_header)
//        addItemType(MultipleItem.TYPE_ORDER, R.layout.layout_my_order)
//        addItemType(MultipleItem.TYPE_BALANCE, R.layout.layout_my_balance)
//        addItemType(MultipleItem.TYPE_TOOLS_HEADER, R.layout.layout_my_tools_header)
//        addItemType(MultipleItem.TYPE_TOOLS, R.layout.layout_my_tools)

        addItemType(MultipleItem.TYPE_COUNT, R.layout.item_list)
        addItemType(MultipleItem.TYPE_ORDER_HEADER, R.layout.item_list)
        addItemType(MultipleItem.TYPE_ORDER, R.layout.item_list)
    }
    override fun convert(holder: BaseViewHolder, item: MultipleItem) {
        when(holder.itemViewType) {
            MultipleItem.TYPE_COUNT -> {
                holder.setText(R.id.item_title, item.mString1)
//                    .addOnClickListener(R.id.my_favoriter)
//                .addOnClickListener(R.id.my_bans)
            }
            MultipleItem.TYPE_ORDER_HEADER->{
                holder.setText(R.id.item_title,item.mString1)
            }
            MultipleItem.TYPE_ORDER->{
                holder.setText(R.id.item_title,item.mString1)
            }
//            MultipleItem.TYPE_ORDER_HEADER -> {
////                holder.addOnClickListener(R.id.ll_my_order)
//            }
//            MultipleItem.TYPE_ORDER -> {
//                holder.setText(R.id.my_order_name, item.mString1)
//                if (item.isShow) {
//                    holder.getView<View>(R.id.my_order_count).visibility = View.VISIBLE
//                    if (item.count > 0) {
//                        if (item.count < 99) {
//                            holder.setText(R.id.my_order_count, String.valueOf(item.count))
//                        } else {
//                            holder.setText(R.id.my_order_count, String.valueOf("99+"))
//                        }
//                    } else {
//                        holder.getView<View>(R.id.my_order_count).visibility = View.GONE
//                    }
//                } else {
//                    holder.getView<View>(R.id.my_order_count).visibility = View.GONE
//                }
//            }
//            MultipleItem.TYPE_BALANCE -> {
//                holder.setText(R.id.my_balance_text, item.mString1)
////                holder.addOnClickListener(R.id.my_balance_btn)
//            }
//            MultipleItem.TYPE_TOOLS_HEADER -> {
//
//            }
//            MultipleItem.TYPE_TOOLS -> {
//                holder.setImageDrawable(
//                    R.id.my_tools_image,
//                    ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)
//                )
//                holder.setText(R.id.my_tools_text, item.mString1)
//                if (item.isShow) {
//                    holder.getView<View>(R.id.my_tools_count).visibility = View.VISIBLE
//                    if (item.count > 0) {
//                        if (item.count < 99) {
//                            holder.setText(R.id.my_tools_count, String.valueOf(item.count))
//                        } else {
//                            holder.setText(R.id.my_tools_count, "99+")
//                        }
//                    } else {
//                        holder.getView<View>(R.id.my_tools_count).visibility = View.GONE
//                    }
//                } else {
//                    holder.getView<View>(R.id.my_tools_count).visibility = View.GONE
//                }
//            }
            
        
        }
    }
}