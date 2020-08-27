package com.jarvis.baselibrary.utils

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import com.jarvis.baselibrary.helper.AppHelper

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/20
 *
 *object 和 companion object 都是生成单例例对象，然后通过单例对象访问 函数和属性的。
 */
object SpUtil {

    /**默认存储的文件名**/
    private var defFileName = "sp_util"

    private fun getShare(fileName: String = defFileName) =
        AppHelper.currentApplication.getSharedPreferences(fileName, MODE_PRIVATE)

    /**
     * 保存数据，数据类型由传入的值确定
     * @throws IllegalArgumentException:数据类型不属于SharedPreferences能保存的类型
     */
    @JvmOverloads
    @SuppressLint("ApplySharedPref")
    fun <T : Any> save(key: String, value: T, fileName: String = defFileName): Boolean =
        with(getShare(fileName).edit()) {
            when (value) {
                is Int -> putInt(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                is Double -> putFloat(key, value.toFloat())
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
                else -> throw IllegalArgumentException("This type can not be saved into SharedPreferences!")
            }.commit()
        }

    /**
     * 取出数据，数据类型由传入的默认值确定
     * @throws IllegalArgumentException:数据类型不属于SharedPreferences能保存的类型
     */
    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    fun <T : Any> get(key: String, defValue: T , fileName: String = defFileName): T =
        with(getShare(fileName)) {
            when (defValue) {
                is Int -> getInt(key, defValue)
                is Float -> getFloat(key, defValue)
                is Long -> getLong(key, defValue)
                is String -> getString(key, defValue)
                is Boolean -> getBoolean(key, defValue)
                is Double -> getFloat(key, defValue.toFloat())
                else -> throw IllegalArgumentException("This type can not be found in SharedPreferences!")
            } as T
        }


    @JvmOverloads
    fun putSpString(key: String, value: String, fileName: String = defFileName): Boolean =
        getShare(fileName).edit().putString(key, value).commit()

    @JvmOverloads
    fun getSpString(key: String, defValue: String = "", fileName: String = defFileName): String =
        getShare(fileName).getString(key, defValue) ?: ""

    @JvmOverloads
    fun putSpBoolean(key: String, value: Boolean, fileName: String = defFileName): Boolean =
        getShare(fileName).edit().putBoolean(key, value).commit()

    @JvmOverloads
    fun getSpBoolean(
        key: String,
        defValue: Boolean = false,
        fileName: String = defFileName
    ): Boolean =
        getShare(fileName).getBoolean(key, defValue)

    @JvmOverloads
    fun putSpStrSet(key: String, value: Set<String>, fileName: String = defFileName): Boolean =
        getShare(fileName).edit().putStringSet(key, value).commit()

    @JvmOverloads
    fun getSpStrSet(key: String, defValue: Set<String>, fileName: String = defFileName): Set<String> =
        getShare(fileName).getStringSet(key, defValue) ?: setOf()

    @JvmOverloads
    fun putSpInt(key: String, value: Int, fileName: String = defFileName): Boolean =
        getShare(fileName).edit().putInt(key, value).commit()

    @JvmOverloads
    fun getSpInt(key: String, defValue: Int = 0, fileName: String = defFileName): Int =
        getShare(fileName).getInt(key, defValue)

    @JvmOverloads
    fun putSpLong(key: String, value: Long, fileName: String = defFileName): Boolean =
        getShare(fileName).edit().putLong(key, value).commit()

    @JvmOverloads
    fun getSpLong(key: String, defValue: Long = 0L, fileName: String = defFileName): Long =
        getShare(fileName).getLong(key, defValue)

    @JvmOverloads
    fun putSpFloat(key: String, value: Float, fileName: String = defFileName): Boolean =
        getShare(fileName).edit().putFloat(key, value).commit()

    @JvmOverloads
    fun getSpFloat(key: String, defValue: Float = 0F, fileName: String = defFileName): Float =
        getShare(fileName).getFloat(key, defValue)


}