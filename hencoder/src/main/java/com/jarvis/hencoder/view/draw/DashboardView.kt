package com.jarvis.hencoder.view.draw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.jarvis.baselibrary.utils.LogUtil
import com.jarvis.baselibrary.utils.dp
import java.util.*
import kotlin.concurrent.timerTask
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/11/24
 */

class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val RADIUS = 150f.dp
    val DASH_WIDTH = 3f.dp
    val DASH_LENGTH = 10f.dp
    val LENGTH = 80f.dp
    val OPEN_ANGLE = 120
    var position = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val dash = Path()
    private val path = Path()

    lateinit var pathEffect: PathDashPathEffect
    var timerTask: TimerTask

    init {
        paint.strokeWidth = 3f.dp
        paint.style = Paint.Style.STROKE
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW)

        timerTask = object : TimerTask() {
            override fun run() {
                if (position >= 20f) {
                    position = 0f
                }
                position += 0.05f
                LogUtil.i("position=${position}")
                postInvalidate()
            }

        }
        Timer().schedule(
            timerTask { timerTask }, 0, 200
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        path.addArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            150f,
            240f
        )

        val pathMeasure = PathMeasure(path, false)

        pathEffect =
            PathDashPathEffect(
                dash,
                (pathMeasure.length - DASH_WIDTH) / 20f,
                0f,
                PathDashPathEffect.Style.ROTATE
            )


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(path, paint)

        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)

        paint.pathEffect = null

        canvas.drawLine(
            width / 2f,
            height / 2f,
            width / 2f + LENGTH * cos(markToRadians(position)).toFloat(),
            height / 2f + LENGTH * sin(markToRadians(position)).toFloat(),
            paint
        )
    }

    private fun markToRadians(mark: Float) =
        Math.toRadians((90 + OPEN_ANGLE / 2 + (360 - OPEN_ANGLE) / 20f * mark).toDouble())


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        timerTask.cancel()
    }
}
