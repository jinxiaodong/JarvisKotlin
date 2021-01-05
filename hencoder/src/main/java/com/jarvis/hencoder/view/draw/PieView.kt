package com.jarvis.hencoder.view.draw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.jarvis.baselibrary.utils.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/11/24
 */

class PieView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val RADIUS = 150f.dp
    val ANGLES = floatArrayOf(60f, 90f, 150f, 60f)
    val COLORS = listOf(
        Color.parseColor("#C2185B"),
        Color.parseColor("#00ACC1"),
        Color.parseColor("#558B2F"),
        Color.parseColor("#5D4037")
    )

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val OFFSET_LEENGTH = 20f.dp


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var startAngle = 0f
        for ((index, angle) in ANGLES.withIndex()) {
            paint.color = COLORS[index]
            if (index == 1) {
                canvas.save()
                canvas.translate(
                    OFFSET_LEENGTH * cos(
                        Math.toRadians((startAngle + angle / 2f).toDouble()).toFloat()
                    ),
                    OFFSET_LEENGTH * sin(
                        Math.toRadians((startAngle + angle / 2f).toDouble()).toFloat()
                    )
                )
            }
            canvas.drawArc(
                width / 2f - RADIUS,
                height / 2f - RADIUS,
                width / 2f + RADIUS,
                height / 2f + RADIUS,
                startAngle,
                angle,
                true,
                paint
            )
            startAngle += angle
            if (index ==1) {
                canvas.restore()

            }
        }

    }


}
