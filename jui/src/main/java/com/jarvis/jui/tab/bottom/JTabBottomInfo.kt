import android.graphics.Bitmap
import android.graphics.Color
import androidx.fragment.app.Fragment
import com.jarvis.jui.tab.bottom.JTabBottomLayout

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/23/21
 */
class JTabBottomInfo<Color> {

    enum class TabType {
        BITMAP, ICON
    }

    var fragment: Class<out Fragment>? = null
    var name: String = ""
    var defaultBitmap: Bitmap? = null
    var selectedBitmap: Bitmap? = null

    /**
     * Tips:Java 代码中设置的iconfont字符串无效，需要定义在string.xml文件中;
     * */
    var iconFont: String? = null
    var defaultIconName: String? = null
    var selectedIconName: String? = null
    var defaultColor: Color? = null
    var tintColor: Color? = null
    var tabType: TabType? = null

    constructor(name: String, defaultBitmap: Bitmap, selectedBitmap: Bitmap) {
        this.name = name
        this.defaultBitmap = defaultBitmap
        this.selectedBitmap = selectedBitmap
        this.tabType = TabType.BITMAP
    }

    constructor(
        name: String,
        iconFont: String,
        defaultIconName: String?,
        selectedIconName: String?,
        defaultColor: Color ,
        tintColor: Color
    ) {
        this.name = name
        this.iconFont = iconFont
        this.defaultIconName = defaultIconName
        this.selectedIconName = selectedIconName
        this.defaultColor = defaultColor
        this.tintColor = tintColor
        this.tabType = TabType.ICON
    }


}
