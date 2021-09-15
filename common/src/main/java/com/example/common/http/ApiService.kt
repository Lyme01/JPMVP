package com.example.common.http



import com.example.common.base.BaseBean
import com.example.common.bean.*
import io.reactivex.Observable
import org.paradisehell.convex.annotation.Transformer
import retrofit2.http.*

interface ApiService {
    /**
     * 版本更新
     */
//    @GET("/wxarticle/chapters/json")
//    fun getData(): Observable<BaseBean<List<DataBean>>>

    @GET("/article/list/{page}/json")
    fun getNews(@Path("page") page: Int):Observable<BaseBean<NewsBean<List<DataX>>>>


    @FormUrlEncoded
    @POST("/user/login")//
    fun login(
        @Field("username") username: String?,
        @Field("password") password: String?
    ):Observable<BaseBean<UserBean>>

    @FormUrlEncoded
    @POST("/user/register")//
    fun register(
        @Field("username") username: String?,
        @Field("password") password: String?, @Field("repassword") repassword: String?,
    ):Observable<BaseBean<UserBean>>


    /**
     * 退出登录
     * https://www.wanandroid.com/user/logout/json
     */
    @GET("user/logout/json")
    fun logout(): Observable<BaseBean<UserBean>>

    /**
     * 个人信息
     */
    @GET("user/lg/userinfo/json")
    fun getUserInfo():Observable<BaseBean<UserInfoBean>>

    @GET("/banner/json")
    fun getBanner():Observable<BaseBean<List<BannerBean>>>


    /**
     * 搜索
     */
    @POST("/article/query/{page}/json")//
    fun search(
        @Path("page") page: Int,
        @Query("k") word: String
    ):Observable<BaseBean<NewsBean<List<DataX>>>>

    /**
     * 添加收藏
     * https://www.wanandroid.com/lg/collect/1165/json
     */
    @POST("lg/collect/{id}/json")
    fun addArticle(@Path("id") id: Int): Observable<BaseBean<NewsBean<List<DataX>>>>

    /**
     * 添加收藏
     * https://www.wanandroid.com/lg/uncollect_originId/2333/json
     */
    @POST("lg/uncollect_originId/{id}/json")
    fun removeArticle(@Path("id") id: Int): Observable<BaseBean<NewsBean<List<DataX>>>>

    /**
     *热词
     */
    @GET("/hotkey/json")
    fun hotKey():Observable<BaseBean<List<HotKeyBean>>>

    /**
     * 收藏  不需要加BaseBean的写法
     */
    @GET("/lg/collect/list/{page}/json")
    @Transformer(WanAndroidConvexTransformer::class)
    fun getCollect( @Path("page") page: Int):Observable<NewsBean<List<DataX>>>
}