package com.example.ui.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes

import com.example.ui.entity.Bug
import com.example.ui.entity.Coordinate
import com.example.ui.entity.Stroke
import com.example.ui.Utility

import java.util.ArrayList

class BugsView : View {
    var bugNumber = 25
    var bugs: ArrayList<Bug> = arrayListOf()
    var strokes: ArrayList<Stroke> = arrayListOf()
    @ColorRes
    var bugColor: Int = -1
    @ColorRes
    var strokeColor: Int = -1
    var drawingStroke: Stroke? = null
    var lastDrawStroke= 0L

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        performClick()
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                drawingStroke = Stroke(Coordinate(event.x,event.y),Coordinate(event.x,event.y),1000)
                invalidate()
                lastDrawStroke = System.currentTimeMillis()
                return true
            }
            MotionEvent.ACTION_UP ->{
                drawingStroke?.let {  strokes.add(it)}
                drawingStroke = null
                return false
            }

            MotionEvent.ACTION_MOVE->{
                if(System.currentTimeMillis() - lastDrawStroke < 30){
                    return true
                }
                if(drawingStroke?.startPoint == drawingStroke?.endPoint){
                    drawingStroke?.endPoint?.x = event.x
                    drawingStroke?.endPoint?.y = event.y
                }else {

                }
                return true
            }
        }

        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawBugs(canvas)
            drawExistedStrokes(canvas)
        }
    }
/*---------------------------------------------------------------------------------------------------------*/

    private fun initView() {
        isClickable = false
        bugColor = ContextCompat.getColor(context, android.R.color.black)
        strokeColor = ContextCompat.getColor(context, android.R.color.holo_red_light)
    }

    private fun drawBugs(canvas: Canvas) {
        if (bugs.isEmpty()) {
            if (bugNumber > 0) {
                for (i in 0 until bugNumber) {
                    val bug = Bug()
                    bug.pos.x = (x + width) / 2
                    bug.pos.y = (y + height) / 2
                    bug.speedVector.x = (-5..5).shuffled().first().toFloat()
                    bug.speedVector.y = (-5..5).shuffled().first().toFloat()
                    bugs.add(bug)
                }
            } else return
        }


        val paint = Paint()
        paint.color = bugColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = Utility.convertDpToPx(context, 3f)

        for (bug in bugs) {
            if (isInside(bug.pos))
                canvas.drawPoint(bug.pos.x, bug.pos.y, paint)
            updateBugPos(bug)
        }
    }


    private fun drawExistedStrokes(canvas: Canvas) {
        if (strokes.isEmpty())
            return
        val paint = Paint()
        paint.color = strokeColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = Utility.convertDpToPx(context, 8f)

        for(stroke in strokes){
            paint.alpha = (255.toFloat() * stroke.displayedTime.toFloat() / 10000.toFloat()).toInt()
            if(stroke.displayedTime > 2000){
                canvas.drawLine(stroke.startPoint.x,stroke.startPoint.y, stroke.endPoint.x, stroke.endPoint.y, paint)
                stroke.displayedTime -= 30
            }
        }
    }


    private fun isInside(point: Coordinate): Boolean = point.x > x && point.x < x + width
            && point.y > y && point.y < y + height

    private fun updateBugPos(bug: Bug) {
        bug.pos.x += bug.speedVector.x
        bug.pos.y += bug.speedVector.y
    }
}
