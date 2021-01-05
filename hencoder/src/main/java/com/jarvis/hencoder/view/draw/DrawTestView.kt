package com.jarvis.hencoder.view.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import com.jarvis.baselibrary.utils.dp

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/11/24
 */


class DrawTestView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val RADIUS = 100f.dp

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val path = Path()

    lateinit var pathMeasure: PathMeasure

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        path.addCircle(width / 2f, height / 2f, RADIUS, Path.Direction.CW)
        path.addRect(
            width / 2f - RADIUS,
            height / 2f,
            width / 2f + RADIUS,
            height / 2f + 2 * RADIUS,
            Path.Direction.CW
        )
        pathMeasure = PathMeasure(path, false)
        pathMeasure.length
        path.fillType = Path.FillType.EVEN_ODD

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        canvas.drawLine(100f, 100f, 200f, 200f, paint)
//        canvas.drawCircle(width / 2f, height / 2f, 150f.px, paint)

        canvas.drawPath(path, paint)

    }

}