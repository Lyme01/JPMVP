package com.example.mine.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

class MultipleItem( override val itemType: Int, var spanSize: Int) : MultiItemEntity {
    var mString1: String? = null
    var mString2: String? = null
    var mString3:String?=null
    var isShow = false
    var count = 0

    companion object {
        const val TYPE_COUNT = 1
        const val TYPE_ORDER_HEADER = 2
        const val TYPE_ORDER = 3
//        const val TYPE_BALANCE = 4
//        const val TYPE_TOOLS_HEADER = 5
//        const val TYPE_TOOLS = 6
    }


}