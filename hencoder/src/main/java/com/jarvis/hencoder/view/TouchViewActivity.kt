package com.jarvis.hencoder.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jarvis.baselibrary.router.constants.ArouterPath
import com.jarvis.hencoder.R

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/12/17
 */
@Route(path = ArouterPath.Hencoder.TouchViewActivity)
class TouchViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hc_activity_touch_view)
    }
}