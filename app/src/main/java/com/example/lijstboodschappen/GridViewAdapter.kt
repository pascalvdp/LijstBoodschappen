package com.example.lijstboodschappen

import android.content.Context
import android.widget.GridView
import android.widget.Toast
import android.widget.AbsListView
import android.view.ViewGroup

class GridViewAdapter internal constructor(var ctx: Context) : GridView(ctx) {

    init {
        var numColumns = 3
        setNumColumns(numColumns)
        val params =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        //params.gravity = Gravity.LEFT;
        //params.width = 450;
        //params.height = 400;// (int) yVerh(300);
        //setMeasuredDimension(params.width, params.height);//params.width
        layoutParams = params
        //setId(View.generateViewsetlon
        //setLongClickable(true);
        //ArrayAdapter arAdapter=new ArrayAdapter(ctx,textViewResourceId);
        //arAdapter.addAll(objects);
        //setAdapter(arAdapter);
    }

    fun toast(obj: Any) {
        Toast.makeText(ctx, obj.toString(), Toast.LENGTH_SHORT).show()
    }
}