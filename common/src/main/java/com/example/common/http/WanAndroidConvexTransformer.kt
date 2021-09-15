package com.example.common.http

import com.example.common.base.BaseBean
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import org.paradisehell.convex.transformer.ConvexTransformer
import java.io.IOException
import java.io.InputStream

// 2. 实现 ConvexTransformer
// 用户将后台返回的数据流转为具体的 data 数据
class WanAndroidConvexTransformer : ConvexTransformer {
    private val gson = Gson()

    @Throws(IOException::class)
    override fun transform(original: InputStream): InputStream {
        // 先反序列化为 BaseResponse
        val response = gson.fromJson<BaseBean<JsonElement>>(
            original.reader(),
            object : TypeToken<BaseBean<JsonElement>>() {
            }.type
        )
        // 判断 Response 是否成功
        // 成功则将 data 数据转换成 InputStream, 最后由具体 Converter 处理
        if (response.errorCode == 0 && response.data != null) {
            return response.data.toString().byteInputStream()
        }
        throw IOException(
            "errorCode : " + response.errorCode + " ; errorMsg : " + response.errorMsg
        )
    }
}