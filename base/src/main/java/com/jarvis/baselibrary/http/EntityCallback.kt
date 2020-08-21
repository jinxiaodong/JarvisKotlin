package com.jarvis.baselibrary.http

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/20
 */
interface EntityCallback<T> {

    fun onSuccess(entity: T)

    fun onFailure(message: String?)
}