package com.jarvis.learnkotlin

import com.jarvis.baselibrary.base.BaseApplication

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/21
 */
class App : BaseApplication() {


    override fun onCreate() {
        super.onCreate()
    }

    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}