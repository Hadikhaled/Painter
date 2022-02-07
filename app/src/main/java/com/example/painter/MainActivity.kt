package com.example.painter

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity()  {


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           setContentView(R.layout.activity_main)
        var rec_Is_Selected : Boolean = false
        var circle_Is_Selected : Boolean = false
        var write_Is_Selected : Boolean = false
        var arrow_Is_Selected : Boolean = false
        val canvas : draw_shipes = draw_shipes(this)
        val Linear = findViewById<LinearLayout>(R.id.linear)
        val pen =findViewById<ImageButton>(R.id.pen)
        val Arrow =findViewById<ImageButton>(R.id.Arrow)
        val Rec =findViewById<ImageButton>(R.id.Rec)
        val Circle =findViewById<ImageButton>(R.id.circle)
        val colors =findViewById<ImageButton>(R.id.colors)
        val colors_list =findViewById<RelativeLayout>(R.id.colors_list)
        val color1 =findViewById<TextView>(R.id.color1)
        val color2 =findViewById<TextView>(R.id.color2)
        val color3 =findViewById<TextView>(R.id.color3)
        Linear.addView(canvas)

        pen.setOnClickListener {


            write_Is_Selected = true
         canvas.write()
         arrow_Is_Selected = false

         circle_Is_Selected = false

         rec_Is_Selected = false
     }
        Arrow.setOnClickListener {

            arrow_Is_Selected = true
            canvas.drawLine()
            circle_Is_Selected = false
            write_Is_Selected = false
            rec_Is_Selected = false
        }
        Rec.setOnClickListener {




            rec_Is_Selected = true
            canvas.drawRect()
           circle_Is_Selected = false
            write_Is_Selected = false
           arrow_Is_Selected = false
        }
        Circle.setOnClickListener {

            circle_Is_Selected = true
            canvas.drawCircle()
            write_Is_Selected = false
            arrow_Is_Selected = false
            rec_Is_Selected = false
        }
        colors.setOnClickListener {

            if(colors_list.visibility == View.GONE){
                colors_list.visibility = View.VISIBLE

            }else{

                colors_list.visibility = View.GONE
            }

        }
        color1.setOnClickListener {

            canvas.paintColor(Color.parseColor("#FB0008"))
        }
        color2.setOnClickListener {

            canvas.paintColor(Color.parseColor("#007F00"))
        }
        color3.setOnClickListener {

            canvas.paintColor(Color.parseColor("#0078DE"))
        }
       if(write_Is_Selected == true){
           canvas.write()
       }

        if(rec_Is_Selected == true ){
            canvas.drawRect()
        }

        if(circle_Is_Selected == true ){
            canvas.drawCircle()

        }

        if(arrow_Is_Selected == true ){
            canvas.drawLine()
        }
    }








}




