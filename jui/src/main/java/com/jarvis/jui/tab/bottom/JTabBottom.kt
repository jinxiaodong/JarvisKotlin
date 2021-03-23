package com.jarvis.jui.tab.bottom

import JTabBottomInfo
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import com.jarvis.jui.R
import com.jarvis.jui.tab.common.IJTab

/**
 * @author jinxiaodong
 * @description：tab 单个控件
 * @date 3/23/21
 */
class JTabBottom @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), IJTab<JTabBottomInfo<*>> {

    lateinit var tabInfo: JTabBottomInfo<*>
    private var tabImageView: ImageView
    private var tabIconView: TextView
    private var tabNameView: TextView

    init {
        val contentView = inflate(context, R.layout.j_tab_bottom, this)
        tabImageView = contentView.findViewById(R.id.iv_image)
        tabIconView = contentView.findViewById(R.id.tv_icon)
        tabNameView = contentView.findViewById(R.id.tv_name)
    }


    override fun setJTabInfo(data: JTabBottomInfo<*>) {
        this.tabInfo = data
        inflateInfo(selected = false, init = true)
    }


    override fun resetHeight(height: Int) {
        layoutParams.height = height
        tabNameView.visibility = View.GONE
    }

    //消息数字
    override fun updateRedPoint(num: Int) {

    }

    override fun onTabSelectedChange(
        index: Int,
        preInfo: JTabBottomInfo<*>?,
        nextInfo: JTabBottomInfo<*>
    ) {
        if (tabInfo != preInfo && nextInfo != tabInfo || preInfo == nextInfo) {
            return
        }
        if (preInfo == tabInfo) {
            //取消选中
            inflateInfo(selected = false, init = false)
        } else {
            inflateInfo(selected = true, init = false)
        }
    }

    private fun inflateInfo(selected: Boolean, init: Boolean) {

        if (tabInfo.tabType == JTabBottomInfo.TabType.ICON) {
            if (init) {
                tabImageView.visibility = View.GONE
                tabIconView.visibility = View.VISIBLE
                val typeface = Typeface.createFromAsset(context.assets, tabInfo.iconFont)
                tabIconView.typeface = typeface
                tabNameView.text = tabInfo.name
            }
            if (selected) {
                tabIconView.text = tabInfo.selectedIconName ?: tabInfo.defaultIconName
                tabIconView.setTextColor(getTextColor(tabInfo.tintColor))
                tabNameView.setTextColor(getTextColor(tabInfo.tintColor))
            } else {
                tabIconView.text = tabInfo.defaultIconName
                tabIconView.setTextColor(getTextColor(tabInfo.defaultColor))
                tabNameView.setTextColor(getTextColor(tabInfo.defaultColor))
            }
        } else if (tabInfo.tabType == JTabBottomInfo.TabType.BITMAP) {

            if (init) {
                tabImageView.visibility = View.VISIBLE
                tabIconView.visibility = View.GONE
                tabNameView.text = tabInfo.name
            }

            if (selected) tabImageView.setImageBitmap(tabInfo.selectedBitmap)
            else tabImageView.setImageBitmap(tabInfo.defaultBitmap)

        }
    }


    @ColorInt
    private fun getTextColor(color: Any?): Int {
        return if (color is String) Color.parseColor(color) else color as Int
    }

}