package com.jarvis.hencoder.view.text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.jarvis.baselibrary.utils.dp
import com.jarvis.baselibrary.utils.getAvatar
import com.jarvis.hencoder.R

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/11/27
 */
class MultilineTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private val IMAGE_SIZE = 200.dp
    private val IMAGE_PADDING = 100.dp
    val text =
        "Lorem ipsum Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin tempus nulla purus, mattis sollicitudin est pretium et. Pellentesque euismod eu lectus laoreet fringilla. Nulla eget viverra elit, efficitur dapibus nibh. Pellentesque sit amet tempor mauris, vitae euismod eros. Etiam non risus et nisl euismod lacinia. Donec ut commodo mauris. In et ornare lorem. Mauris pharetra dui a congue interdum.dolor sit amet, consectetur adipiscing elit. Proin tempus nulla purus, mattis sollicitudin est pretium et. Pellentesque euismod eu lectus laoreet fringilla. Nulla eget viverra elit, efficitur dapibus nibh. Pellentesque sit amet tempor mauris, vitae euismod eros. Etiam non risus et nisl euismod lacinia. Donec ut commodo mauris. In et ornare lorem. Mauris pharetra dui a congue interdum."


    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }
    private val bitmap = getAvatar(IMAGE_SIZE, R.mipmap.avatar)
    private val fonMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        staticLayout = StaticLayout.Builder.obtain(text, 0, text.length, textPaint, width)
//            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
//            .setLineSpacing(0f, 1f)
//            .setIncludePad(false)
//            .build()
//        staticLayout.draw(canvas)

        canvas.drawBitmap(bitmap, width - IMAGE_SIZE, IMAGE_PADDING, textPaint)
        textPaint.getFontMetrics(fonMetrics)
        val measureWidth = floatArrayOf(0f)
        var start = 0
        var count: Int
        var verticalOffset = -fonMetrics.top

        var maxWidth: Float
        while (start < text.length) {
            if (verticalOffset + fonMetrics.bottom < IMAGE_PADDING
                || verticalOffset + fonMetrics.top > IMAGE_PADDING + IMAGE_SIZE
            ) {
                maxWidth = width.toFloat()
            } else {
                maxWidth = width.toFloat() - IMAGE_SIZE
            }
            count =
                textPaint.breakText(text, start, text.length, true, maxWidth, measureWidth)
            canvas.drawText(text, start, start + count, 0f, verticalOffset, textPaint)
            start += count
            verticalOffset += textPaint.fontSpacing
        }

    }
}