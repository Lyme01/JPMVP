package com.example.common.http



import com.example.common.base.BaseBean
import com.example.common.bean.NewsBean
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    /**
     * 版本更新
     */
//    @GET("/wxarticle/chapters/json")
//    fun getData(): Observable<BaseBean<List<DataBean>>>

    @GET("/article/list/0/json")
    fun getNews():Observable<BaseBean<NewsBean>>

//    @GET("/article/listproject/0/json")
//    fun getTab():Observable<BaseBean<TabBean>>
}