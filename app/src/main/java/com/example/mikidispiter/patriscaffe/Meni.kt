package com.example.mikidispiter.patriscaffe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import kotlinx.android.synthetic.main.activity_meni.*
import android.widget.RelativeLayout.*
import android.view.ViewGroup
import android.support.annotation.IdRes
import android.support.annotation.RequiresApi
import android.widget.*
import com.example.mikidispiter.patriscaffe.R.id.*
import kotlinx.android.synthetic.main.activity_meni.view.*
import java.util.ArrayList


class Meni : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meni)
    }

    var selected = ""


    fun Kafa(view: View) {
        if (selected == "") {
            when (view) {
                Turska -> {
                    selected = "Turska"; kava = 0
                }
                Latte -> {
                    selected = "Latte"; kava = 1
                }
                Capucino -> {
                    selected = "Capucino"; kava = 2
                }
                Moca -> {
                    selected = "Moca"; kava = 3
                }
                Maroco -> {
                    selected = "Maroco"; kava = 4
                }
                Instant -> {
                    selected = "Instant"; kava = 5
                }
                Frapucino -> {
                    selected = "Frapucino"; kava = 6
                }
                Brazil -> {
                    selected = "Brazil"; kava = 7
                }
                else -> {
                    selected = "pogresna kafa!??"; kava = 8
                }
            }
        } else {
            brojac = 1
            selected = ""
        }
        bieda()
        closePrevs()
    }


    fun bieda() {
        if (counterbieda < 2) {

            turskaDodaci.addAll(listOf(turskaSlag1, turskaSecer1, turskaSlag2, turskaSecer2))
            latteDodaci.addAll(listOf(latteSlag1, latteSecer1, latteSlag2, latteSecer2))

            for (i in turskaDodaci) {
                lista.add(i)
            }
            for (i in latteDodaci) {
                lista.add(i)
            }

            lista2.addAll(listOf(turska2, latte2, turska1, latte1))

            lista3.addAll(listOf(turskaBrojac, latteBrojac))

            lista4.addAll(listOf(turskaSecer2, turskaSlag2, latteSecer2, latteSlag2))

            listaDodaci.addAll(listOf(turskaDodaci, latteDodaci))

        }
        counterbieda += 1
    }

    fun closePrevs() {
        when (selected) {
            "Turska" -> turska1.visibility = View.VISIBLE
            "Latte" -> latte1.visibility = View.VISIBLE
            else -> closeAll()
        }
    }

    fun closeAll() {
        for (i in lista) {
            i.isChecked = false
        }
        for (i in lista2) {
            i.visibility = View.GONE
        }
        for (i in lista3) {
            i.text = brojac.toString()
        }
    }


    var counterbieda = 1
    var lista = ArrayList<CheckBox>(20)
    var lista2 = ArrayList<LinearLayout>(20)
    var lista3 = ArrayList<TextView>(20)
    var lista4 = ArrayList<CheckBox>(20)
    var listaDodaci = ArrayList<ArrayList<CheckBox>>(20)

    var turskaDodaci = ArrayList<CheckBox>(4)
    var latteDodaci = ArrayList<CheckBox>(4)
    var brojac: Int = 1
    var dodajNaRacun: Int = 0
    var ukupnaCena: Int = 0

    var kava: Int = 0

    fun kafaVise(view: View) {
        when (view) {
            turskaVise -> {
                kava = 0
            }
            latteVise -> {
                kava = 1
            }

        }
        brojac = lista3[kava].text.toString().toInt()
        if (brojac < 2) {
            brojac += 1
            lista3[kava].text = brojac.toString()
            lista2[kava].visibility = View.VISIBLE
        }

    }

    fun kafaManje(view: View) {

        when (view) {
            turskaManje -> {
                kava = 0
            }
            latteManje -> {
                kava = 1
            }

        }
        brojac = lista3[kava].text.toString().toInt()
        if (brojac > 1) {
            brojac -= 1
            lista3[kava].text = brojac.toString()
            lista2[kava].visibility = View.GONE
            lista4[2 * (kava + 1) - 2].isChecked = false
            lista4[2 * (kava + 1) - 1].isChecked = false
        }

    }


    fun dodaj(view: View) {

        var dodaci = ArrayList<Boolean>(4)
        var dodatak1: Boolean = false
        var dodatak2: Boolean = false
        var dodatak3: Boolean = false
        var dodatak4: Boolean = false
        dodaci.addAll(listOf(dodatak1, dodatak2, dodatak3, dodatak4))
        var cenaKafe: Int


//        when (selected) {
//            "Turska" -> {
//
//                    for (i in dodaci.indices){dodaci[i] = turskaDodaci[i].isChecked}
//                    cenaKafe = 90
//            }
//            "Latte" -> {
//                    for (i in dodaci.indices){dodaci[i] = latteDodaci[i].isChecked}
//                    cenaKafe = 150
//            }
//            else -> {cenaKafe = 0}
//
//
//        }
        for (i in dodaci.indices) {
            dodaci[i] = listaDodaci[kava][i].isChecked
        }
        cenaKafe = 90
        dodajNaRacun += brojac * cenaKafe
        if (dodaci[0]) dodajNaRacun += 10
        if (dodaci[1]) dodajNaRacun += 15
        if (dodaci[2]) dodajNaRacun += 10
        if (dodaci[3]) dodajNaRacun += 15

        selected = ""
        brojac = 1
        closePrevs()
        ukupnaCena += dodajNaRacun
        dodajNaRacun = 0
        buttonPorudzbina.text = ukupnaCena.toString()
    }


}




