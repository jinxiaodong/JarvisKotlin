@file:JvmName("KotlinUtis") //java 可以直接通过KotlinUtis.dp2px 调用 kotlin的包级函数

package com.jarvis.baselibrary.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.jarvis.baselibrary.BaseApplication

/**
 * @author jinxiaodong
 * @description：工具类
 * @date 2020/8/20
 */

private val displayMetrics = Resources.getSystem().displayMetrics;

//扩展函数
fun Float.dp2px(): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics);
}

fun String.isMobile(){
    return
}

//扩展

object Utils {
//    private val displayMetrics = Resources.getSystem().displayMetrics;
//
//    fun dp2px(dp: Float): Float {
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
//    }

    // @JvmOverloads  可以使java 文件 能访问到 Kotlin 的重载参数
    @JvmOverloads
    fun toast(string: String, duration: Int = Toast.LENGTH_SHORT) {
        var ss :String = ""
        ss.isMobile()
        Toast.makeText(BaseApplication.currentApplication, string, duration).show()
    }



}