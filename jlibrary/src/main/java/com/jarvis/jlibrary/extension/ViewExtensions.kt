package com.jarvis.jlibrary.extension

import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/23/21
 */

/**
 * 获取指定类型的子View
 *
 * @param group viewGroup
 * @param cls   如：RecyclerView.class
 * @param <T>
 * @return 指定类型的View
</T> */


fun <T> ViewGroup.findTypeView(cls: Class<T>): T? {
    val deque = ArrayDeque<View>()

    deque.add(this)
    while (deque.isNotEmpty()) {
        val node = deque.removeFirst()
        if (cls.isInstance(node)) {
            return cls.cast(node)
        } else if (node is ViewGroup) {
            for (i in 0 until node.childCount) {
                deque.add(node.getChildAt(i))
            }
        }
    }
    return null
}