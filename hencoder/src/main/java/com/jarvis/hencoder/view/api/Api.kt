package com.jarvis.hencoder.view.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/9/21
 */

interface Api {

    @GET("users/{username}/repos")
    fun getRepos(@Path("username") username: String): Single<MutableList<Repo>>

}

data class Repo(var name: String)