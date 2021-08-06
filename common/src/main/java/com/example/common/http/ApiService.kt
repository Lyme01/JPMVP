package com.example.common.http



import com.example.common.base.BaseBean
import com.example.common.bean.BannerBean
import com.example.common.bean.DataX
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
    fun getNews(@Path("num") num: Int):Observable<BaseBean<NewsBean<List<DataX>>>>


    @FormUrlEncoded
    @POST("/user/login")//
    fun login(@Field("username") username: String?,
        @Field("password") password: String? ):Observable<BaseBean<UserBean>>

    @FormUrlEncoded
    @POST("/user/register")//
    fun register(@Field("username") username: String?,
                 @Field("password") password: String?,@Field("repassword") repassword: String?,):Observable<BaseBean<UserBean>>


    @GET("/banner/json")
    fun getBanner():Observable<BaseBean<List<BannerBean>>>



    @POST("/article/query/{page}/json")//
    fun search(@Path("page") page:Int,
               @Query("k")  word:String ):Observable<BaseBean<NewsBean<List<DataX>>>>
}