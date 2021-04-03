package com.nio.gateway;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

import java.io.IOException;

/**
 * @program: week02
 * @description: OkHttpClient
 * @author: 王小欢
 * @create: 2021-03-26 23:41
 **/
public class OkHttpClientDemo {
    public static String OkHttpClient(String url) throws IOException {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        return request.body().toString();
    }

    public static void main(String[] args) throws IOException {
        String url="http://localhost:8808";
        OkHttpClient(url);
    }
}
