package com.jarvis.learnkotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.jarvis.baselibrary.utils.dp2px
import com.jarvis.learnkotlin.R
import java.util.*

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/20
 */
class CodeView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null) :

    AppCompatTextView(context, attr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = getContext().getColor(R.color.base_colorAccent)
        strokeWidth = 6f.dp2px()
    }

    private val codeList = arrayOf(
        "kotlin",
        "android",
        "java",
        "http",
        "https",
        "okhttp",
        "retrofit",
        "tcp/ip"
    )

    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(getContext().getColor(R.color.base_colorPrimary))
        setTextColor(Color.WHITE)
        updateCode()
    }

    fun updateCode() {
        val random = Random().nextInt(codeList.size)
        val code = codeList[random]
        text = code
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(0f, height.toFloat(), width.toFloat(), 0f, paint)
        super.onDraw(canvas)
    }




}

