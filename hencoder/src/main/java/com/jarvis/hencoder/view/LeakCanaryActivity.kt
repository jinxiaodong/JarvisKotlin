package com.jarvis.hencoder.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jarvis.baselibrary.router.constants.ArouterPath
import com.jarvis.hencoder.R

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/9/21
 */

@Route(path = ArouterPath.Hencoder.LeakCanaryActivity)
class LeakCanaryActivity : AppCompatActivity() {


    private lateinit var click: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hc_activity_leakcanary)
        click = findViewById(R.id.click)

        click.setOnClickListener {

        }
    }
}