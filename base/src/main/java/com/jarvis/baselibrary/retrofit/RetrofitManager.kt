package com.jarvis.baselibrary.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/21
 */


object RetrofitManager {

    val RETROFIT = Retrofit.Builder()
        .baseUrl("https://api.hencoder.com/")
        .build()

    inline fun <reified T> create(): T {
        return RETROFIT.create(T::class.java)
    }

}


interface API {

    @GET("lessons")
    fun lessons(): Call<Any>
}

fun main() {
    println(RetrofitManager.create<API>())
}