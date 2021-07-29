package com.example.common.base


data class BaseBean<T>(
    val errorCode: Int,
    val errorMsg: String,
    var data:T?
)