package debug

import com.jarvis.baselibrary.base.BaseApplication
import com.jarvis.hencoder.BuildConfig

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/27
 */
class ModuleAccplication : BaseApplication() {

    override fun isDebug() = BuildConfig.DEBUG

    override fun onCreate() {
        super.onCreate()
    }
}
