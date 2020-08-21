package com.jarvis.baselibrary

import android.app.Application
import android.content.Context

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/20
 */
class BaseApplication : Application() {

    companion object {
        @JvmStatic
        //@get:JvmName(currentApplication)该注解可以使java 文件直接通过BaseApplication.currentApplication方式访问到currentApplication
        @get:JvmName("currentApplication")
        lateinit var currentApplication: Context
            private set //set 方法私有
    }

    override fun onCreate() {
        super.onCreate()
        currentApplication = this
    }

}