package com.example.lijstboodschappen

import android.content.Context
import android.graphics.Color
import android.widget.FrameLayout
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.Drawable
import android.view.View.OnTouchListener
import android.view.MotionEvent
import android.widget.Toast
import android.view.ViewGroup

//ConstraintLayout toegevoegd via dependencies
class Product internal constructor(var ctx: Context) : FrameLayout(ctx) {
    var dX = 0f
    var dY = 0f
    var shape = GradientDrawable()
    var frmL: FrameLayout? = null

    init {
        shape.cornerRadius = 8f
        shape.setColor(Color.RED)
        shape.setSize(400, 400)
        //float []fl={8f,0f,0f,0f};
        //shape.setCornerRadii(fl);
        background = shape
        this.x = 100f
        val params =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        //params.gravity = Gravity.LEFT;
        //params.width = 450;
        //params.height = 400;// (int) yVerh(300);
        //setMeasuredDimension(params.width, params.height);//params.width
        layoutParams = params
    }

    override fun getX(): Float {
        // TODO: Implement this method
        return super.getX()
    }

    override fun getBackground(): Drawable {
        // TODO: Implement this method
        return super.getBackground()
    }

    override fun setOnClickListener(l: OnClickListener?) {
        toast("onclick")
        super.setOnClickListener(l)
    }

    override fun setOnTouchListener(l: OnTouchListener) {
        // TODO: Implement this method
        super.setOnTouchListener(l)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                dX = (-width / 2).toFloat()
                dY = (-height / 2).toFloat()
                animate()
                    .x(getX() + event.x + dX) //event.getRawX() + dX
                    .y(getY() + event.y + dY) //event.getRawY() + dY
                    .setDuration(0)
                    .start()
                toast("action down")
            }
            MotionEvent.ACTION_MOVE ->                //event.getRawX=getX+event.getX
                //event.getRawY houdt geen rekening met statusbar bovenaan waardoor je
                //dit niet kunt gebruiken, enkel Y dus
                animate()
                    .x(getX() + event.x + dX) //event.getRawX() + dX
                    .y(getY() + event.y + dY) //event.getRawY() + dY
                    .setDuration(0)
                    .start()
            MotionEvent.ACTION_UP -> toast("action up")
        }
        return true //false
        //return super.onTouchEvent(event);
    }

    fun toast(obj: Any) {
        Toast.makeText(ctx, obj.toString(), Toast.LENGTH_SHORT).show()
    }


}