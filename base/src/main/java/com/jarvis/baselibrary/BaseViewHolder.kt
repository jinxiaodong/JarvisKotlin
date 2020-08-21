package com.jarvis.baselibrary

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * @author jinxiaodong
 * @description：Kotlin 中 类默认不能被继承 （被final 修饰）
 *              使用abstract open override 关键字 声明该类可以被继承/重写方法
 * @date 2020/8/20
 */
 abstract  class BaseViewHolder : RecyclerView.ViewHolder {


    constructor(itemView: View) : super(itemView)


    @SuppressLint("UseSparseArrays")
    private val viewHashMap: MutableMap<Int, View?> = HashMap()

    protected open fun <T : View?> getView(@IdRes id: Int): T? {
        var view = viewHashMap[id]
        if (view == null) {
            view = itemView.findViewById(id)
            viewHashMap[id] = view
        }
        return view as T?
    }

    protected open fun setText(@IdRes id: Int, text: String?) {
        (getView<View>(id) as TextView).text = text
    }
}