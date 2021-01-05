package com.jarvis.hencoder.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.jarvis.baselibrary.router.constants.ArouterPath
import com.jarvis.hencoder.R

@Route(path = ArouterPath.Hencoder.HCXferModeActivity)
class HCViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hc_activity_xfermode)
    }
}