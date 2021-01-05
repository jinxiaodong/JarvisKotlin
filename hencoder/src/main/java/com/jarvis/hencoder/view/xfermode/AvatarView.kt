package com.jarvis.hencoder.view.xfermode

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.jarvis.baselibrary.utils.dp
import com.jarvis.hencoder.R

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/11/25
 */
class AvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val IMAGE_WIDTH = 150f.dp
    private val IMAGE_PADDING = 20f.dp


    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val bounds = RectF(
        IMAGE_PADDING,
        IMAGE_PADDING,
        IMAGE_PADDING + IMAGE_WIDTH,
        IMAGE_PADDING + IMAGE_WIDTH
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val saveLayer = canvas.saveLayer(bounds, null)
        canvas.drawOval(
            IMAGE_PADDING,
            IMAGE_PADDING,
            IMAGE_PADDING + IMAGE_WIDTH,
            IMAGE_PADDING + IMAGE_WIDTH, paint
        )
        paint.xfermode = xfermode

        canvas.drawBitmap(
            getAvatar(IMAGE_WIDTH, R.mipmap.avatar),
            IMAGE_PADDING,
            IMAGE_PADDING, paint
        )
        paint.xfermode = null
        canvas.restoreToCount(saveLayer)
    }


    fun getAvatar(width: Float, resourcesId: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, resourcesId, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width.toInt()
        return BitmapFactory.decodeResource(resources, resourcesId, options)
    }
}