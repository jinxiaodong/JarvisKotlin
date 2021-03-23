package com.jarvis.learnkotlin

import com.google.gson.Gson
import com.jarvis.baselibrary.base.BaseApplication
import com.jarvis.jlibrary.log.JConsolePrinter
import com.jarvis.jlibrary.log.JLogConfig
import com.jarvis.jlibrary.log.JLogManager

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/21
 */
class App : BaseApplication() {


    override fun onCreate() {
        super.onCreate()

        initLog()
    }

    private fun initLog() {
        JLogManager.init(object : JLogConfig() {

            override fun injectJsonParser(): JsonParser {
                return JsonParser { src -> Gson().toJson(src) }
            }

            override fun getGlobalTag(): String {
                return "Jarvis"
            }

            override fun enable(): Boolean {
                return isDebug();
            }
        },JConsolePrinter())
    }

    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}