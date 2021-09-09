package com.example.common.bean

data class UserInfoBean(
    val coinInfo: CoinInfo,
    val userInfo: UserBean
)

data class CoinInfo(
    val coinCount: Int,
    val level: Int,
    val nickname: String,
    val rank: String,
    val userId: Int,
    val username: String
)

