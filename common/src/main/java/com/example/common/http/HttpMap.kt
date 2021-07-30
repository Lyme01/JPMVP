package com.example.common.http

import java.util.HashMap

/**
 * @author ChenMo
 * 接口请求公共参数
 */
class HttpMap : HashMap<String, Any>() {
    companion object {
        val httpMap: HttpMap
            get() = HttpMap()
    }

}
