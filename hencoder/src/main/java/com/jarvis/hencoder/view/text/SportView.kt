package com.jarvis.hencoder.view.text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.jarvis.baselibrary.utils.dp
import com.jarvis.baselibrary.utils.sp

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/11/27
 */
class SportView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private val CIRCLE_COLOR = Color.parseColor("#90A4AE")
    private val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")
    private val RING_WIDTH = 20.dp
    private val RADIUS = 150.dp


    private val bounds = Rect()

    private val fontMetrics = Paint.FontMetrics()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 100.dp
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = Paint.Style.STROKE
        paint.color = CIRCLE_COLOR
        paint.strokeWidth = RING_WIDTH
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)


        paint.color = HIGHLIGHT_COLOR
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            -90f,
            225f,
            false,
            paint
        )

        paint.textSize = 100.dp
        paint.style = Paint.Style.FILL
        paint.getFontMetrics(fontMetrics)
        paint.getTextBounds("abab", 0, "abab".length, bounds)
        canvas.drawText(
            "ababp",
            width / 2f,
            height / 2f - (fontMetrics.ascent + fontMetrics.descent) / 2f,
            paint
        )

        //绘制文字2
        paint.textSize = 150.dp
        paint.textAlign = Paint.Align.LEFT
        paint.getFontMetrics(fontMetrics)
        paint.getTextBounds("abab", 0, "abab".length, bounds)
        canvas.drawText("abab", 0f - bounds.left, 0f - bounds.top, paint)


        //绘制文字3
        paint.textSize = 15.dp
        paint.textAlign = Paint.Align.LEFT
        paint.getTextBounds("abab", 0, "abab".length, bounds)
        paint.getFontMetrics(fontMetrics)
        canvas.drawText("abab", 0f - bounds.left, 0f - bounds.top, paint)


    }
}