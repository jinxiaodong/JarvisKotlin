package com.jarvis.baselibrary.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.jarvis.baselibrary.helper.AppHelper
import com.jarvis.baselibrary.utils.LogUtil
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/20
 */
abstract class BaseApplication : Application() {

    abstract fun isDebug(): Boolean

    override fun onCreate() {
        super.onCreate()
        AppHelper.init(this)
        //日志初始化
        initLogger()
        //初始化Arouter
        initArouter()
    }

    private fun initArouter() {
        if (isDebug()) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }


    private fun initLogger() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("Jarvis")
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return isDebug()
            }
        })
        Logger.t("Learn_Kotlin")

        LogUtil.init(isDebug())
    }

}