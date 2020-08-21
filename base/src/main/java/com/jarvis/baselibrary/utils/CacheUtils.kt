package com.jarvis.baselibrary.utils

import android.content.Context
import com.jarvis.baselibrary.BaseApplication
import com.jarvis.baselibrary.R

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/20
 *
 *object 和 companion object 都是生成单例例对象，然后通过单例对象访问 函数和属性的。
 */
object CacheUtils {
    private val context = BaseApplication.currentApplication

    private val SP = context.getSharedPreferences(
        context.getString(R.string.base_app_name),
        Context.MODE_PRIVATE
    )

    fun save(key: String?, value: String?) = SP.edit().putString(key, value).apply()

    fun get(key: String?) = SP.getString(key, null)


}