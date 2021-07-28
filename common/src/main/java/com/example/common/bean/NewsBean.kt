package com.example.common.bean

data class NewsBean(
        val `data`: List<Data>,
        val page: String,
        val pageSize: String,
        val stat: String
)

data class Data(
        val author_name: String,
        val category: String,
        val date: String,
        val is_content: String,
        val thumbnail_pic_s: String,
        val thumbnail_pic_s02: String,
        val thumbnail_pic_s03: String,
        val title: String,
        val uniquekey: String,
        val url: String
)

