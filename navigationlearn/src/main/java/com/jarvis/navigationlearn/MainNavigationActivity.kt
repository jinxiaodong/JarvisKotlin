package com.jarvis.navigationlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.jarvis.baselibrary.router.constants.ArouterPath

@Route(path = ArouterPath.Navigationlearn.MainNavigationActivity)
class MainNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_activity_main_navigation)
        lifecycleScope
    }
}
