package com.jarvis.hencoder.view.xfermode

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.jarvis.baselibrary.utils.dp
import com.jarvis.baselibrary.utils.parsePorterDuffMode
import com.jarvis.baselibrary.utils.sp
import com.jarvis.hencoder.R

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/11/25
 */
class XfermodeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val PADING = 0f.dp
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = RectF(PADING, 50f.dp, PADING + 150f.dp, 200f.dp)
    private var xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private var text = "SRC_IN"
    private val circleBitmap =
        Bitmap.createBitmap(150f.dp.toInt(), 150f.dp.toInt(), Bitmap.Config.ARGB_8888)
    private val sauqreBitmap =
        Bitmap.createBitmap(150f.dp.toInt(), 150f.dp.toInt(), Bitmap.Config.ARGB_8888)


    init {
        val styleAttrs = context.obtainStyledAttributes(attrs, R.styleable.hc_xfermodeviewstyle)
        val mode = styleAttrs.getInteger(R.styleable.hc_xfermodeviewstyle_hc_porterduffxfermode, 5)
        text = styleAttrs.getString(R.styleable.hc_xfermodeviewstyle_hc_text) ?: "SRC_IN"
        styleAttrs.recycle()
        xfermode = PorterDuffXfermode(mode.parsePorterDuffMode(PorterDuff.Mode.SRC_IN))
        val canvas = Canvas(circleBitmap)
        paint.color = Color.parseColor("#D81B60")
        canvas.drawOval(PADING + 50f.dp, 0f, PADING + 150f.dp, 100f.dp, paint)

        canvas.setBitmap(sauqreBitmap)
        paint.color = Color.parseColor("#2196F3")
        canvas.drawRect(PADING + 0f, 50f.dp, PADING + 100f.dp, 150f.dp, paint)

//        attrs.getAttributeIntValue(R.)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val saveLayer = canvas.saveLayer(bounds, null)
        canvas.drawBitmap(circleBitmap, 0f, 0f.dp, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(sauqreBitmap, 0f.dp, 0f.dp, paint)
        canvas.restoreToCount(saveLayer)
        paint.xfermode = null

        paint.textSize = 16.sp
        paint.color = Color.parseColor("#000000")
        canvas.drawText(text, width / 2f - 20.dp, height - 10.dp, paint)
    }


}