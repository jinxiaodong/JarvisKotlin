package com.jarvis.jui.tab.common

import androidx.annotation.Px

/**
 * @author jinxiaodong
 * @description：Tab对外接口
 * @date 3/23/21
 */
interface IJTab<D> : IJTablayout.OnTabSelectedListener<D> {

    fun setJTabInfo(data: D)

    /**
     * 动态修改某个Item的大小
     * @param height
     */
    fun resetHeight(@Px height: Int)

    fun updateRedPoint(num: Int) {}
}
