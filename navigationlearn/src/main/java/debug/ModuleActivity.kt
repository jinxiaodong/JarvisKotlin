package debug

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.jarvis.baselibrary.R
import com.jarvis.baselibrary.router.constants.ArouterPath

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/27
 */
class ModuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity_module_test)
        findViewById<Button>(R.id.btn_click).setOnClickListener {
            ARouter.getInstance()
                .build(ArouterPath.Navigationlearn.MainNavigationActivity)
                .navigation()
        }
    }
}