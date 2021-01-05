package com.jarvis.hencoder.view.scalableimageview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.jarvis.baselibrary.utils.dp
import com.jarvis.baselibrary.utils.getAvatar
import com.jarvis.hencoder.R
import kotlin.math.max
import kotlin.math.min


/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/12/15
 */


class ScaleableImageView(context: Context?, attrs: AttributeSet) : View(context, attrs) {
    private val IMAGE_SIZE = 300.dp
    private val EXTRA_SCALE_FACTOR = 10f

    private val bitmap = getAvatar(IMAGE_SIZE, R.mipmap.avatar)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var ofiginalOffsetX = 0f
    private var ofiginalOffsetY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private var minScale = 0f
    private var maxScale = 0f

    private var isBig = false
    private var isFling = false
    private var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }


    private val scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", minScale, maxScale)


    private val henGestureListener = HenGestureListener()
    private val henFlingRunner = HenFlingRunner()
    private val scaleGestureListener = HenScalGestureListener()


    private val gestureDetector = GestureDetectorCompat(context, henGestureListener)

    private val scaleGestureDetector = ScaleGestureDetector(context, scaleGestureListener)

    private val scoller = OverScroller(context)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        ofiginalOffsetX = (width - IMAGE_SIZE) / 2
        ofiginalOffsetY = (height - IMAGE_SIZE) / 2

        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            minScale = width / bitmap.width.toFloat()
            maxScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
        } else {
            minScale = height / bitmap.height.toFloat()
            maxScale = width / bitmap.width.toFloat() * EXTRA_SCALE_FACTOR
        }
        currentScale = minScale

        scaleAnimator.setFloatValues(minScale, maxScale)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val scaleFraction = (currentScale - minScale) / (maxScale - minScale)
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, ofiginalOffsetX, ofiginalOffsetY, paint)

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        isFling = false
        scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
        }

        return true
    }


    private fun fixOffsets() {
        offsetX = min(offsetX, (bitmap.width * maxScale - width) / 2f)
        offsetX = max(offsetX, -(bitmap.width * maxScale - width) / 2f)
        offsetY = min(offsetY, (bitmap.height * maxScale - height) / 2f)
        offsetY = max(offsetY, -(bitmap.height * maxScale - height) / 2f)
    }


    inner class HenGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        /**
         *
         */
        override fun onFling(
            downEvent: MotionEvent?,
            currentEvent: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (isBig) {
                scoller.fling(
                    offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                    (-(bitmap.width * maxScale - width) / 2f).toInt(),
                    ((bitmap.width * maxScale - width) / 2f).toInt(),
                    (-(bitmap.height * maxScale - height) / 2f).toInt(),
                    ((bitmap.height * maxScale - height) / 2f).toInt()
                )
            }
            ViewCompat.postOnAnimation(this@ScaleableImageView, henFlingRunner)
            isFling = true
            return true
        }

        /**
         * 当触摸的时候则会调用
         * @param distanceX 距离，旧位置 - 新位置
         * @param distanceY
         */
        override fun onScroll(
            downEvent: MotionEvent?,
            currentEvent: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (isBig) {
                offsetX -= distanceX
                offsetY -= distanceY

                fixOffsets()
                invalidate()
            }
            return false
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            //双击 间隔300mm
            isBig = !isBig
            if (isBig) {
                offsetX = (e.x - width / 2f) * (1 - maxScale / minScale)
                offsetY = (e.y - height / 2f) * (1 - maxScale / minScale)
                fixOffsets()
                scaleAnimator.start()
            } else {
                scaleAnimator.reverse()
            }
            return true

        }
    }


    inner class HenFlingRunner : Runnable {

        override fun run() {

            if (scoller.computeScrollOffset() && isFling) {
                scoller.computeScrollOffset()
                offsetX = scoller.currX.toFloat()
                offsetY = scoller.currY.toFloat()
                invalidate()
                ViewCompat.postOnAnimation(this@ScaleableImageView, this)
            }

        }

    }


    inner class HenScalGestureListener : ScaleGestureDetector.OnScaleGestureListener {

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            offsetX = (detector.focusX - width / 2f) * (1 - maxScale / minScale)
            offsetY = (detector.focusY - height / 2f) * (1 - maxScale / minScale)
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val tempScale = currentScale * detector.scaleFactor
            return if (tempScale < minScale || tempScale > maxScale) {
                false
            } else {
                currentScale *= detector.scaleFactor
                true
            }
        }

    }
}