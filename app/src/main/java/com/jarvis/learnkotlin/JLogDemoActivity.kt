package com.jarvis.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jarvis.baselibrary.router.constants.ArouterPath
import com.jarvis.jlibrary.log.*

class JLogDemoActivity : AppCompatActivity() {

    lateinit var viewPrinter: JViewPrinter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_j_log_demo)
        viewPrinter = JViewPrinter(this);

        viewPrinter.viewProvider.showFloatingView()
    }

    fun btnClick(view: View) {
        JLogManager.getInstance().addPrinter(viewPrinter)
        JLog.log(object :JLogConfig(){
            override fun includeThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }

        },JLogType.E,"-----","5566")
        JLog.e("9900")
    }
}