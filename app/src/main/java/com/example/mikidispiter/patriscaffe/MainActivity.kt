package com.example.mikidispiter.patriscaffe

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun Meni(view: View){
        val OtvoriProzor2 = Intent(this, Meni::class.java)
        startActivity(OtvoriProzor2)
    }

}
