package debug

import com.jarvis.baselibrary.base.BaseApplication
import com.jarvis.navigationlearn.BuildConfig

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
