package com.jarvis.baselibrary.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/11/27
 */


fun View.getAvatar(width: Float, resourcesId: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, resourcesId, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width.toInt()
    return BitmapFactory.decodeResource(resources, resourcesId, options)

}