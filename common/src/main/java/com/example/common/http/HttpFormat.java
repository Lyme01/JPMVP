package com.example.common.http;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @author rg65544
 * @date 2018/6/7
 * 网络数据格式
 * */
public class HttpFormat {

    public static RequestBody fixedFormat(Map<String, Object> params){
        Gson gson = new Gson();
        String json = gson.toJson(params);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), json);
        return body;
    }
}
