package com.jarvis.hencoder.view.text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.jarvis.baselibrary.utils.dp

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/12/2
 */
class MaterialEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val TEXT_SIZE = 12.dp

    private val TEXT_MARGIN = 8.dp

    private val HORIZONTAL_OFFSET = 5.dp

    private val VERITICAL_OFFSET = 23.dp

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        setPadding(
            paddingLeft,
            (paddingTop + TEXT_SIZE + TEXT_MARGIN).toInt(),
            paddingRight,
            paddingBottom
        )

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!text.isNullOrEmpty()) {
            canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, VERITICAL_OFFSET, paint)
        }
    }
}