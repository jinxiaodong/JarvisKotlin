package com.jarvis.jui.tab.common

import android.view.ViewGroup

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/23/21
 */
interface IJTablayout<Tab : ViewGroup, D> {

    fun findTab(data: D): Tab?


    fun addTabSelectedChangeListener(listener: OnTabSelectedListener<D>)


    fun defaultSelected(defaultInfo: D)

    fun inflateInfo(infoList: MutableList<D>)

    interface OnTabSelectedListener<D> {
        fun onTabSelectedChange(index: Int, preInfo: D?, nextInfo: D)
    }
}