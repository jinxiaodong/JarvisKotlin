package com.jarvis.baselibrary.utils

import android.text.TextUtils
import android.util.Log
import com.orhanobut.logger.Logger

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/21
 */
object LogUtil {

    var isLog = true

    fun init(debugable: Boolean) {
        isLog = debugable
    }

    fun v(content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.v(content)
        }
    }

    fun v(TAG: String?, content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.t(TAG).v(content)
        }
    }

    fun v(TAG: String?, content: String?, tr: Throwable?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Log.v(TAG, content, tr)
        }
    }

    fun d(content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.d(content)
        }
    }

    fun d(TAG: String?, content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.t(TAG).d(content)
        }
    }

    fun d(TAG: String?, content: String?, tr: Throwable?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Log.d(TAG, content, tr)
        }
    }

    fun e(content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.e(content)
        }
    }

    fun e(TAG: String?, content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.t(TAG).e(content)
        }
    }

    fun e(TAG: String?, content: String?, tr: Throwable?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Log.e(TAG, content, tr)
        }
    }

    fun i(content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.i(content)
        }
    }

    fun i(TAG: String?, content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.t(TAG).i(content)
        }
    }

    fun i(TAG: String?, content: String?, tr: Throwable?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Log.i(TAG, content, tr)
        }
    }

    fun wtf(content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.wtf(content)
        }
    }

    fun wtf(TAG: String?, content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.t(TAG).wtf(content)
        }
    }


    fun w(content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.w(content)
        }
    }

    fun w(TAG: String?, content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.t(TAG).w(content)
        }
    }

    fun w(TAG: String?, content: String?, tr: Throwable?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Log.w(TAG, content, tr)
        }
    }


    fun printStackTrace(t: Throwable?) {
        if (isLog && t != null) t.printStackTrace()
    }


    fun json(TAG: String?, content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.t(TAG).json(content)
        }
    }

    fun json(content: String?) {
        if (isLog) {
            if (TextUtils.isEmpty(content)) {
                return
            }
            Logger.json(content)
        }
    }


}