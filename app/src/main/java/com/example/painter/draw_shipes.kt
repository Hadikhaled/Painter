package com.example.painter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import kotlin.math.sqrt

class draw_shipes(context: Context, attrs: AttributeSet?) : View(context, attrs){
    constructor(context: Context) : this(context, null) {

    }

    private var path = Path()
    private var isRect : Boolean = false
    private var isLine : Boolean = false
    private var isCircle : Boolean = false

    private var iswrite : Boolean = false
    private var paintColor : Int = Color.BLACK

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private var StX : Float = 0f
    private var StY : Float = 0f

    private val paint = Paint().apply {
        color =  paintColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND

      //  strokeWidth = STROKE_WIDTH
    }

    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    private var currentX = 0f

    private var currentY = 0f

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
        if(isRect || isLine || isCircle){
            StX = currentX
            StY = currentY
        }
    }

    private fun touchMove() {
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)
        if (dx >= touchTolerance || dy >= touchTolerance) {

            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY




        }

        invalidate()
    }

    private fun touchUp() {
        if(isRect){
            extraCanvas.drawRect(StX,StY,currentX,currentY,paint)

        }
        if(isLine){
            extraCanvas.drawLine(StX,StY,currentX,currentY,paint)

        }

        if(isCircle){
            extraCanvas.drawCircle(StX,StY, sqrt(((currentX-StX)*(currentX-StX))+((currentY-StY)*(currentY-StY))),paint)

        }
        if(iswrite){

            extraCanvas.drawPath(path, paint)


        }

       path.reset()
    }

    fun paintColor(color : Int){
        paint.color = color
    }

   fun write(){

       isLine = false
       isRect = false
       isCircle = false
     iswrite = true

   }


    fun drawRect(){
        isLine = false
        isRect = true
        isCircle = false
        iswrite = false
    }

    fun drawLine(){
        isRect = false
        isLine = true
        isCircle = false
        iswrite = false
    }

    fun drawCircle(){
        iswrite = false
        isRect = false
        isLine = false
        isCircle = true
    }


}