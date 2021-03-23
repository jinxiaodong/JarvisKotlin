package com.jarvis.jui.tab.bottom

import JTabBottomInfo
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.FrameLayout
import android.widget.ScrollView
import androidx.recyclerview.widget.RecyclerView
import com.jarvis.jlibrary.extension.dp
import com.jarvis.jlibrary.extension.findTypeView
import com.jarvis.jlibrary.log.JDisplayUtil
import com.jarvis.jui.R
import com.jarvis.jui.tab.common.IJTablayout


/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/23/21
 */
class JTabBottomLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), IJTablayout<JTabBottom, JTabBottomInfo<*>> {

    companion object {
        private val FL_CONTENT_TAG = "FL_CONTENT_TAG"
    }

    private val tabSelectedChangeListeners =
        mutableListOf<IJTablayout.OnTabSelectedListener<JTabBottomInfo<*>>>()

    private var selectedInfo: JTabBottomInfo<*>? = null

    var bottomAlpha = 1f

    //tabBottom高度
    var tabBottomHeight = 50f

    //tabbottom头部线条高度
    var bottomLineHeight = 0.5f

    //tabbottom 头部线条颜色
    var bottomLineColor = "#dfe0e1"

    private var infoList: MutableList<JTabBottomInfo<*>>? = null


    override fun findTab(data: JTabBottomInfo<*>): JTabBottom? {
        val view = findViewWithTag<ViewGroup>(FL_CONTENT_TAG)
        for (i in 0 until view.childCount) {
            val tab = view.getChildAt(i)
            if (tab is JTabBottom && tab.tabInfo == data) {
                return tab
            }
        }
        return null
    }

    override fun addTabSelectedChangeListener(listener: IJTablayout.OnTabSelectedListener<JTabBottomInfo<*>>) {
        tabSelectedChangeListeners.add(listener)

    }

    override fun defaultSelected(defaultInfo: JTabBottomInfo<*>) {
    }

    override fun inflateInfo(infoList: MutableList<JTabBottomInfo<*>>) {
        if (infoList.isEmpty()) {
            return
        }
        this.infoList = infoList
//        移除之前已经添加的View
        for (i in childCount - 1 downTo 1) {
            removeViewAt(i)
        }
        selectedInfo = null
        addBackground()

        //移除 JTabBottom 监听
        val iterator = tabSelectedChangeListeners.iterator()
        while (iterator.hasNext()) {
            if (iterator.next() is JTabBottom) {
                iterator.remove()
            }
        }

        val height = tabBottomHeight.dp.toInt()
        val frameLayout = FrameLayout(context)
        frameLayout.tag = FL_CONTENT_TAG
        val screenWidth = JDisplayUtil.getDisplayWidthInPx(context) / infoList.size
        for (i in 0 until infoList.size) {
            val info = infoList[i]
            //Tips: 使用LinearLayout:当动态改变child 大小后Gravity.BOTTOM会失效
            val layoutParams = LayoutParams(screenWidth, height)
            layoutParams.gravity = Gravity.BOTTOM
            layoutParams.leftMargin = i * screenWidth
            val jTabBottom = JTabBottom(context)
            tabSelectedChangeListeners.add(jTabBottom)
            jTabBottom.setJTabInfo(info)
            jTabBottom.setOnClickListener { onSelected(info) }
            frameLayout.addView(jTabBottom, layoutParams)

        }

        val flParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        flParams.gravity = Gravity.BOTTOM
        addBottomLine()
        addView(frameLayout, flParams)
        
        fixContentView()

    }


    private fun onSelected(nextInfo: JTabBottomInfo<*>) {
        for (listener in tabSelectedChangeListeners) {

            listener.onTabSelectedChange(infoList?.indexOf(nextInfo) ?: 0, selectedInfo, nextInfo)
        }
        this.selectedInfo = nextInfo
    }


    private fun addBottomLine() {
        val bottomLine = View(context)
        bottomLine.setBackgroundColor(Color.parseColor(bottomLineColor))

        val bottomLayoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, bottomLineHeight.dp.toInt())
        bottomLayoutParams.gravity = Gravity.BOTTOM
        bottomLayoutParams.bottomMargin = (tabBottomHeight - bottomLineHeight).dp.toInt()

        bottomLine.alpha = bottomAlpha

        addView(bottomLine, bottomLayoutParams)

    }

    /**
     * 背景色
     */
    private fun addBackground() {
        val bg = View.inflate(context, R.layout.j_bottom_layout_bg, null)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, tabBottomHeight.dp.toInt())
        params.gravity = Gravity.BOTTOM
        addView(bg, params)
    }


    /**
     *修复内容底部边距
     */
    private fun fixContentView() {
        val firstChild = getChildAt(0)
        if (firstChild !is ViewGroup) {
            return
        }

        var target: ViewGroup? = firstChild.findTypeView(RecyclerView::class.java)
        if (target == null) {
            target = firstChild.findTypeView(ScrollView::class.java)
        }
        if (target == null) {
            target = firstChild.findTypeView(AbsListView::class.java)
        }
        target?.setPadding(0, 0, 0, tabBottomHeight.dp.toInt())
        target?.clipToPadding = false

    }

}