package com.example.common.base


data class BaseBean<T>(
    var result: T?,
    val error_code: Int,
    val reason: String
)