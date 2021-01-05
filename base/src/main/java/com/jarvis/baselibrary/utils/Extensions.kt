package com.jarvis.baselibrary.utils

import android.content.res.Resources
import android.graphics.PorterDuff
import android.util.TypedValue
import android.view.View

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/11/24
 */

/**
 * 扩展float转像素
 * */
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.dp
    get() = this.toFloat().dp

val Float.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.sp
    get() = this.toFloat().sp


fun Int.parsePorterDuffMode( defaultMode: PorterDuff.Mode): PorterDuff.Mode {
    return when (this) {
        0 -> PorterDuff.Mode.CLEAR
        1 -> PorterDuff.Mode.SRC
        2 -> PorterDuff.Mode.DST
        3 -> PorterDuff.Mode.SRC_OVER
        4 -> PorterDuff.Mode.DST_OVER
        5 -> PorterDuff.Mode.SRC_IN
        6 -> PorterDuff.Mode.DST_IN
        7 -> PorterDuff.Mode.SRC_OUT
        8 -> PorterDuff.Mode.DST_OUT
        9 -> PorterDuff.Mode.SRC_ATOP
        10 -> PorterDuff.Mode.DST_ATOP
        11 -> PorterDuff.Mode.XOR
        12 -> PorterDuff.Mode.DARKEN
        13 -> PorterDuff.Mode.MULTIPLY
        14 -> PorterDuff.Mode.SCREEN
        15 -> PorterDuff.Mode.OVERLAY
        16 -> PorterDuff.Mode.ADD
        17 -> PorterDuff.Mode.LIGHTEN
        else -> defaultMode
    }



}


