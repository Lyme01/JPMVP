package com.example.common.util

import android.content.Context
import android.widget.Toast

object Utils {
    // 已在Init类初始化
    lateinit var mContext: Context

    fun showToast(content: String) {
        Toast.makeText(mContext, content, Toast.LENGTH_LONG).show()
    }
}