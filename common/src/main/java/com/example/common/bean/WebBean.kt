package com.example.common.bean

import java.io.Serializable

class WebBean : Serializable {
    var url: String? = null
    var title: String? = null
    var position = 0
    var id = 0
    var isCollect = false
    var isShowIcon = true
    var originId = 0
    override fun toString(): String {
        return "WebBean{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", position=" + position +
                ", id=" + id +
                ", isCollect=" + isCollect +
                ", isShowIcon=" + isShowIcon +
                ", originId=" + originId +
                '}'
    }
}