package com.example.lijstboodschappen

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var but: Button
    //var but = Button(this) //dit is niet ok op deze positie

    override fun onCreate(savedInstanceState: Bundle?) { //ttttt
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        but = findViewById(R.id.button_goTo)
        but.setOnClickListener{
            //een activity class maak je het best via activity zodat dit in manifest komt te staan
            val intent = Intent(this, CardActivity::class.java)
            // To pass any data to next activity
            //intent.putExtra("keyIdentifier", value)
            // start your next activity
            startActivity(intent)
            //finish()
        }
    }

    fun toast(obj: Any) {
        Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show()
    }
}