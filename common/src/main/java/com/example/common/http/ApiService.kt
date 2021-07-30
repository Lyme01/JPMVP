package com.example.common.http



import com.example.common.base.BaseBean
import com.example.common.bean.NewsBean
import com.example.common.bean.UserBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    /**
     * 版本更新
     */
//    @GET("/wxarticle/chapters/json")
//    fun getData(): Observable<BaseBean<List<DataBean>>>

    @GET("/article/list/{num}/json")
    fun getNews(@Path("num") num: Int):Observable<BaseBean<NewsBean>>
    @FormUrlEncoded
    @POST("/user/login")//
    fun login(@Field("username") username: String?,
        @Field("password") password: String? ):Observable<BaseBean<UserBean>>

}