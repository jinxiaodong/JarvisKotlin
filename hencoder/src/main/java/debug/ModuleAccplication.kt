package debug

import com.jarvis.baselibrary.base.BaseApplication
import com.jarvis.hencoder.BuildConfig
import com.squareup.leakcanary.LeakCanary

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/27
 */
class ModuleAccplication : BaseApplication() {

    override fun isDebug() = BuildConfig.DEBUG

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        val install = LeakCanary.install(this)
    }
}
