package com.example.lijstboodschappen

import android.content.Context
//import com.example.lijstboodschappen.Item.getbirdName
//import com.example.lijstboodschappen.Item.getbirdImage
import android.widget.ArrayAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.lijstboodschappen.R
import android.widget.TextView
import android.widget.Toast
import java.util.ArrayList

class MyAdapter(context: Context?, textViewResourceId: Int, objects: ArrayList<Item>) :
    ArrayAdapter<Any?>(
        context!!, textViewResourceId, objects as List<Any?>
    ) {
    var ctx: Context? = context
    var birdList = ArrayList<Item>()
    init {
        birdList = objects

        //toast("constructed");
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        v = inflater.inflate(R.layout.grid_view_items, null)
        val textView = v.findViewById<TextView>(R.id.textView)
        val imageView = v.findViewById<ImageView>(R.id.imageView)
        textView.text = birdList[position].getbirdName()
        imageView.setImageResource(birdList[position].getbirdImage())
        return v
    }

    fun toast(obj: Any) {
        Toast.makeText(ctx, obj.toString(), Toast.LENGTH_SHORT).show()
    }


}