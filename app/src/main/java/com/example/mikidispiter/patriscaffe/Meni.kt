package com.example.mikidispiter.patriscaffe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import kotlinx.android.synthetic.main.activity_meni.*
import android.widget.RelativeLayout.*
import android.view.ViewGroup
import android.widget.LinearLayout
import android.support.annotation.IdRes
import android.support.annotation.RequiresApi
import android.widget.RelativeLayout
import com.example.mikidispiter.patriscaffe.R.id.*
import kotlinx.android.synthetic.main.activity_meni.view.*


class Meni : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meni)
    }

    var selected = ""



    fun Turska(view: View){
        if (selected == "Turska") {
            brojac = 1
            selected = ""

        } else {
            selected = "Turska"
        }
        closePrevs()
    }

    fun closePrevs() {
        when (selected) {
            "Turska" -> {
                turska1.visibility = View.VISIBLE
            }
            else -> {
                turska1.visibility = View.GONE
                turska2.visibility = View.GONE
                turskaSecer1.isChecked = false
                turskaSlag1.isChecked = false
                turskaSlag2.isChecked = false
                turskaSecer2.isChecked = false
                turskaBrojac.text = brojac.toString()
            }
        }


    }


    var brojac: Int = 1
    var dodajNaRacun: Int = 0
    var ukupnaCena: Int = 0

    fun turskaVise(view: View) {
        brojac = turskaBrojac.text.toString().toInt()
        if (brojac < 2) {
            brojac += 1
            turskaBrojac.text = brojac.toString()
            turska2.visibility = View.VISIBLE
        }

    }

    fun turskaManje(view: View) {
        brojac = turskaBrojac.text.toString().toInt()
        if (brojac > 1) {
            brojac -= 1
            turskaBrojac.text = brojac.toString()
            turska2.visibility = View.GONE
            turskaSlag2.isChecked = false
            turskaSecer2.isChecked = false
        }
    }

    fun dodaj(view: View) {
        var dodatak1: Boolean
        var dodatak2: Boolean
        var dodatak3: Boolean
        var dodatak4: Boolean

        when (selected) {
            "Turska" -> {
                if (brojac == 1) {
                    dodatak1 = (turskaSlag1.isChecked); dodatak2 = turskaSecer1.isChecked; dodatak3 = false; dodatak4 = false;dodajNaRacun += 80
                } else {
                    dodatak1 = (turskaSlag1.isChecked); dodatak2 = turskaSecer1.isChecked;dodatak3 = (turskaSlag2.isChecked); dodatak4 = turskaSecer2.isChecked;dodajNaRacun += 160
                }
                if (dodatak1) {
                    dodajNaRacun += 10
                };if (dodatak2) {
                    dodajNaRacun += 15
                };if (dodatak3) {
                    dodajNaRacun += 10
                };if (dodatak4) {
                    dodajNaRacun += 15
                }
            }


        }

        selected = ""
        brojac = 1
        closePrevs()
        ukupnaCena += dodajNaRacun
        dodajNaRacun = 0
        buttonPorudzbina.text = ukupnaCena.toString()
    }


}


