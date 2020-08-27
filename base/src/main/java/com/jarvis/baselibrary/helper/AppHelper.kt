package com.jarvis.baselibrary.helper

import android.annotation.SuppressLint
import android.app.Application
import com.jarvis.baselibrary.base.BaseApplication

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/21
 */
object AppHelper {

    @JvmStatic
    //@get:JvmName(currentApplication)该注解可以使java 文件直接通过BaseApplication.currentApplication方式访问到currentApplication
    @get:JvmName("currentApplication")
    lateinit var currentApplication: Application
        private set

    fun init(app: Application) {
        currentApplication = app
    }


}