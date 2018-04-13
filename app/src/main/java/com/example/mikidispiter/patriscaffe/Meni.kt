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
        bieda() //inicializacia arraylistov (len 1 raz sa sprevadza)
    }

//Inicializacia promenljivih aj valsov

    var lista2 = ArrayList<LinearLayout>(20)
    var lista3 = ArrayList<TextView>(20)
    var lista4 = ArrayList<CheckBox>(20)
    var listaDodaci = ArrayList<ArrayList<CheckBox>>(20)
    var listaCena = ArrayList<TextView>(20)
    var turskaDodaci = ArrayList<CheckBox>(4)
    var latteDodaci = ArrayList<CheckBox>(4)

    var kava: Int = 8
    var counterbieda = 1
    var brojac: Int = 1
    var dodajNaRacun: Int = 0
    var ukupnaCena: Int = 0

    //    Tu sa 1-raz inicializuju listi skrz modularnosti kodu (mali bi bit oznacenie z adekvatnima menami)
//    Abi sa primenili secke kavi potrebne je len poplnit listi aj linkuvat xml
    fun bieda() {
        if (counterbieda < 2) {
            counterbieda += 1

            turskaDodaci.addAll(listOf(turskaSlag1, turskaSecer1, turskaSlag2, turskaSecer2))
            latteDodaci.addAll(listOf(latteSlag1, latteSecer1, latteSlag2, latteSecer2))

            lista2.addAll(listOf(turska2, latte2, turska1, latte1))

            lista3.addAll(listOf(turskaBrojac, latteBrojac))

            lista4.addAll(listOf(turskaSecer2, turskaSlag2, latteSecer2, latteSlag2))

            listaDodaci.addAll(listOf(turskaDodaci, latteDodaci))

            listaCena.addAll(listOf(cenaTurska, cenaLatte))
        }
    }


    //    Tento selektor bude robit zakazdim ked sa klikne edom z hlavnich layoutov kazdej vrste kave
    fun Kafa(view: View) {
        if (kava == 8) {
            when (view) {
                Turska -> kava = 0
                Latte -> kava = 1
                Capucino -> kava = 2
                Moca -> kava = 3
                Maroco -> kava = 4
                Instant -> kava = 5
                Frapucino -> kava = 6
                Brazil -> kava = 7
                else -> kava = 8
            }
        } else kava = 8
        managePrevs()
    }


    //    Upravljania z prikazom pod-meniov (otvori odhovarajuci alebo zatvori secke u zavisnosti od selektovanej kave)
    fun managePrevs() {
        if (kava < 8) lista2[kava + lista2.size / 2].visibility = View.VISIBLE
        else closeAllPrevs()
    }

    //    Zatvarania seckich pod-meniov
    fun closeAllPrevs() {
        brojac = 1
        for (i in listaDodaci.indices) {
            for (j in listaDodaci[i].indices) {
                listaDodaci[i][j].isChecked = false
            }
        }
        for (i in lista2) {
            i.visibility = View.GONE
        }
        for (i in lista3) {
            i.text = brojac.toString()
        }
    }


    //    Zveci broj kavov, otvori 2. pod meni, refreshuje brojac
    fun kafaVise(view: View) {
        brojac = lista3[kava].text.toString().toInt()
        if (brojac < 2) {
            brojac += 1
            lista3[kava].text = brojac.toString()
            lista2[kava].visibility = View.VISIBLE
        }

    }

    //    Zmensi broj kavov, zatvori 2. pod meni, vipise novi brojac, *v buducnosti: pozve funkciju za zatvarania checkboxu
    fun kafaManje(view: View) {
        brojac = lista3[kava].text.toString().toInt()
        if (brojac > 1) {
            brojac -= 1
            lista3[kava].text = brojac.toString()
            lista2[kava].visibility = View.GONE
            lista4[2 * (kava + 1) - 2].isChecked = false
            lista4[2 * (kava + 1) - 1].isChecked = false
        }

    }


    //    Dodaj kavi na racun (zateraz sa aktivira klikalim gombika Pogledaj Racun)
//    dostane vrednosti dodatkov (z checkboxov)
//    formira cenu shodne vibratej kave aj vibratima dodatkami, vipise cenu
//    restartuje layouti (selektuvana kava je 8(ani edna z kavov))
    fun dodaj(view: View) {

        var dodaci = ArrayList<Boolean>(4)
        var dodatak1: Boolean = false
        var dodatak2: Boolean = false
        var dodatak3: Boolean = false
        var dodatak4: Boolean = false
        dodaci.addAll(listOf(dodatak1, dodatak2, dodatak3, dodatak4))

        for (i in dodaci.indices) {
            dodaci[i] = listaDodaci[kava][i].isChecked
        }

        dodajNaRacun += brojac * listaCena[kava].text.toString().toInt()
        if (dodaci[0]) dodajNaRacun += 10
        if (dodaci[1]) dodajNaRacun += 15
        if (dodaci[2]) dodajNaRacun += 10
        if (dodaci[3]) dodajNaRacun += 15

        kava = 8
        managePrevs()
        ukupnaCena += dodajNaRacun
        dodajNaRacun = 0
        buttonPorudzbina.text = ukupnaCena.toString()
    }


}




