package com.example.mikidispiter.patriscaffe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_meni.*

class Meni : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meni)
    }

    fun Turska(view: View){
        textView3.text = "Prava domacinska besecera"
    }
}
