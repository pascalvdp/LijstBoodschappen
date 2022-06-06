package com.example.lijstboodschappen

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class ListActivity : AppCompatActivity(){

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

    }*/

    //4de test na save in google drive
    /*
            //een activity class maak je het best via activity zodat dit in manifest komt te staan
            val intent = Intent(this, ListActivity::class.java)
            // To pass any data to next activity
            //intent.putExtra("keyIdentifier", value)
            // start your next activity
            startActivity(intent)
            //finish()

            //??????????????????????
            inline fun <reified T: MainActivity> Context.createIntent() =
        Intent(this, T::class.java)

    inline fun <reified T: MainActivity> MainActivity.startActivity() {
        startActivity(createIntent<T>())
    }
            */
    var ctx: Context? = null
    var linLayout: LinearLayout? = null

    //GridView simpleList;
    var birdList = ArrayList<Item>()
    lateinit var myAdapter: MyAdapter
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        linLayout = findViewById(R.id.list)

        //linLayout?.setOnDragListener(dragListener)

        val relativeLayout = RelativeLayout(this)
        val relativeLayout2 = RelativeLayout(this)
        val relativeLayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT, 600
        )
        relativeLayout.layoutParams = relativeLayoutParams
        relativeLayout.setBackgroundColor(Color.GREEN)
        relativeLayout2.layoutParams = relativeLayoutParams
        relativeLayout2.setBackgroundColor(Color.WHITE)
        linLayout?.addView(relativeLayout)
        linLayout?.addView(relativeLayout2)
        //val testView = View.inflate(this,R.layout.grid_view_items,null) as RelativeLayout
        val testView = TextView(this)
        testView.textSize = 20f
        testView.text = "This is a dynamic TextView"
        relativeLayout2.addView(testView)
        testView.setOnLongClickListener{
            val clipText = "this is data text from test"
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(it?.tag.toString(), mimeTypes, item)
            val dragShadowbuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data,dragShadowbuilder,it,0)
            true
        }

        //Product prod=new Product(this);
        //linLayout.addView(prod);
        relativeLayout?.setOnDragListener(dragListener)
        relativeLayout2?.setOnDragListener(dragListener)
        //simpleList = findViewById(R.id.simpleGridView);
        birdList.add(Item("Bird 1", R.drawable.hearts2))
        birdList.add(Item("Bird 2", R.drawable.hearts2))
        birdList.add(Item("Bird 3", R.drawable.hearts2))
        birdList.add(Item("Bird 4", R.drawable.hearts2))
        birdList.add(Item("Bird 5", R.drawable.hearts2))
        birdList.add(Item("Bird 6", R.drawable.hearts2))
        birdList.add(Item("Bird 7", R.drawable.hearts2))
        birdList.add(Item("Bird 8", R.drawable.hearts2))
        //birdList.add(new Item("Bird 9",R.drawable.hearts2));
        val gridViewAdapter = GridViewAdapter(this)
        linLayout?.addView(gridViewAdapter)
        myAdapter = MyAdapter(this, R.layout.grid_view_items, birdList)
        gridViewAdapter.adapter = myAdapter
        gridViewAdapter.onItemClickListener = AdapterView.OnItemClickListener { parent, view, pos, id ->
                view.setBackgroundColor(Color.RED)
                val owner = view.parent as ViewGroup
                owner.setBackgroundColor(Color.RED)
                //owner.removeView(view)
                toast("test klik")
            }
        gridViewAdapter.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, pos, id ->
                /*val clipText = "this is ourclipdaata text"
                val item = ClipData.Item(clipText)
                val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                val data = ClipData(clipText,mimeTypes,item)
                */
                //val test = ClipboardManager().primaryClip.Cl
                //getClipboardManager().primaryClip = ClipData.newPlainText("text", value)
                //val tag = view?.tag.toString() //=null
                //val tag = view?.getTag(view?.tag as Int) as String?
                //val tag = view?.setTag(id.toInt()).toString()
                //val tag = view?.tag.toString()    //view.setTag(view).toString()
                //val item = ClipData.Item(view?.tag as CharSequence)
                //val textvi = parent.getChildAt(1)   ?????????????????????????????????????????????
                val clipText = "this is ourclipdaata text"
                val item = ClipData.Item(clipText)
                //toast(tag)
                //val item = ClipData.Item(tag)
                val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                //val data = ClipData(tag,mimeTypes,item)
                val data = ClipData(view?.tag.toString(), mimeTypes, item)

                val dragShadowbuilder = View.DragShadowBuilder(view)
                view.startDragAndDrop(data, dragShadowbuilder, view, 0)
                //view.visibility = View.INVISIBLE
                //toast("test long klik")
                true
            }
    }

    val dragListener = View.OnDragListener { view, event ->
        when(event.action){
            DragEvent.ACTION_DRAG_STARTED ->{
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
            DragEvent.ACTION_DRAG_ENTERED ->{
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> { true }
            DragEvent.ACTION_DRAG_EXITED ->{
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP ->{
                val item = event.clipData.getItemAt(0)
                val dragData = item.text.toString()
                toast(dragData)
                view.invalidate()
                val v = event.localState as View
                //view.setBackgroundColor(Color.RED)
                val owner = v.parent as ViewGroup
                //owner.setBackgroundColor(Color.RED)
                //owner.removeView(v) //is not supported in AdapterView
                owner.removeViewInLayout(v)

                myAdapter.notifyDataSetChanged()
                val destination = view as RelativeLayout //LinearLayout
                //destination.setBackgroundColor(Color.RED)
                //val destinParent = destination.parent as ViewGroup
                //destinParent.removeView(v)
                destination.addView(v)
                v.visibility = View.VISIBLE

                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                true
            }
            else -> false
        }
    }

    fun toast(obj: Any) {
        Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show()
    }
}