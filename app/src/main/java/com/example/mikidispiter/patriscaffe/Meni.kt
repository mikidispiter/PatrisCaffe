package com.example.mikidispiter.patriscaffe

import android.graphics.Color
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
var listaPodMenii = ArrayList<LinearLayout>(20)
    var listaBrojaci = ArrayList<TextView>(20)
    var listaDodaci2PodMeni = ArrayList<CheckBox>(20)
    var listaDodaci = ArrayList<ArrayList<CheckBox>>(20)
    var listaCena = ArrayList<TextView>(20)
    var turskaDodaci = ArrayList<CheckBox>(4)
    var latteDodaci = ArrayList<CheckBox>(4)
    var capucinoDodaci = ArrayList<CheckBox>(4)
    var mocaDodaci = ArrayList<CheckBox>(4)
    var marocoDodaci = ArrayList<CheckBox>(4)
    var instantDodaci = ArrayList<CheckBox>(4)
    var frapucinoDodaci = ArrayList<CheckBox>(4)
    var brazilDodaci = ArrayList<CheckBox>(4)


    class Kava(b: TextView, c: LinearLayout, d: LinearLayout, e: CheckBox, f: CheckBox, g: CheckBox, h: CheckBox, i: TextView, j: Button, k: Button, l: Int) {
        val IsSelected = l
        val cena = b.text.toString().toInt()
        val prviPodMeniVidljivost = c.visibility
        val druhiPodMeniVidljivost = d.visibility
        val prviSlagCheckT = e.isChecked
        val prviSecerCheckT = f.isChecked
        val druhiSlagCheckT = g.isChecked
        val druhiSecerCheckT = h.isChecked
        val brojac = i.text.toString().toInt()
        val manje = j
        val vise = k
    }





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

            var TurskaK = Kava(cenaTurska, turska1, turska2, turskaSlag1, turskaSecer1, turskaSlag2, turskaSecer2, turskaBrojac, turskaManje, turskaVise, 0)





//            secke dodatke v kazdom podmeniu posebne za kazdu kavu sortiranie (a1 a2 a3 a4)
            turskaDodaci.addAll(listOf(turskaSlag1, turskaSecer1, turskaSlag2, turskaSecer2))
            latteDodaci.addAll(listOf(latteSlag1, latteSecer1, latteSlag2, latteSecer2))
            capucinoDodaci.addAll(listOf(capucinoSlag1, capucinoSecer1, capucinoSlag2, capucinoSecer2))
            mocaDodaci.addAll(listOf(mocaSlag1, mocaSecer1, mocaSlag2, mocaSecer2))
            marocoDodaci.addAll(listOf(marocoSlag1, marocoSecer1, marocoSlag2, marocoSecer2))
            instantDodaci.addAll(listOf(instantSlag1, instantSecer1, instantSlag2, instantSecer2))
            frapucinoDodaci.addAll(listOf(frapucinoSlag1, frapucinoSecer1, frapucinoSlag2, frapucinoSecer2))
            brazilDodaci.addAll(listOf(brazilSlag1, brazilSecer1, brazilSlag2, brazilSecer2))
//            secke podmenii (sortiranie a1 a2 b1 b2)
            listaPodMenii.addAll(listOf(turska1, turska2, latte1, latte2, capucino1, capucino2, moca1, moca2, maroco1, maroco2,
                    instant1, instant2, frapucino1, frapucino2, brazil1, brazil2))

//            textview brojaci
            listaBrojaci.addAll(listOf(turskaBrojac, latteBrojac, capucinoBrojac, mocaBrojac, marocoBrojac, instantBrojac, frapucinoBrojac, brazilBrojac))


//            secke dodatke v kazdom podmeniu dovena
            listaDodaci.addAll(listOf(turskaDodaci, latteDodaci, capucinoDodaci, mocaDodaci, marocoDodaci, instantDodaci, frapucinoDodaci, brazilDodaci))

            //            secke dodatke v druhom podmeniu sortiranie a1 a2 b1 b2
            for (i in listaDodaci.indices) for (j in listaDodaci[i].indices) if (j > 1) listaDodaci2PodMeni.add(listaDodaci[i][j])

//            textview ceni
            listaCena.addAll(listOf(cenaTurska, cenaLatte, cenaCapucino, cenaMoca, cenaMaroco, cenaInstant, cenaFrapucino, cenaBrazil))
        }
    }


    //    Tento selektor bude robit zakazdim ked sa klikne edom z hlavnich layoutov kazdej vrste kave
    fun Kafa(view: View) {
        if (kava == 8) {
            kava = when (view) {
                Turska -> 0
                Latte -> 1
                Capucino -> 2
                Moca -> 3
                Maroco -> 4
                Instant -> 5
                Frapucino -> 6
                Brazil -> 7
                else -> 8
            }
            managePrevs()
        } else closeAllPrevs()
    }


    //    Upravljania z prikazom pod-meniov (otvori odhovarajuci alebo zatvori secke u zavisnosti od selektovanej kave)
    fun managePrevs() {
        if (kava < 8) listaPodMenii[((kava + 1) * 2) - 2].visibility = View.VISIBLE
        if (kava == 7) scrollView2.scrollTo(0, pomocni.bottom)
    }

    //    Zatvarania seckich pod-meniov
    fun closeAllPrevs() {
        brojac = 1

        for (i in listaDodaci[kava]) i.isChecked = false

        listaPodMenii[((kava + 1) * 2) - 2].visibility = View.GONE
        listaPodMenii[((kava + 1) * 2) - 1].visibility = View.GONE

        listaBrojaci[kava].text = brojac.toString()

        kava = 8
    }


    //    Zveci broj kavov, otvori 2. pod meni, refreshuje brojac
    fun kafaVise(view: View) {
        brojac = listaBrojaci[kava].text.toString().toInt()
        if (brojac < 2) {
            brojac += 1
            listaBrojaci[kava].text = brojac.toString()
            listaPodMenii[((kava + 1) * 2) - 1].visibility = View.VISIBLE
        }
        if (kava == 7) scrollView2.scrollTo(0, pomocni.bottom)
    }

    //    Zmensi broj kavov, zatvori 2. pod meni, vipise novi brojac, *v buducnosti: pozve funkciju za zatvarania checkboxu
    fun kafaManje(view: View) {
        brojac = listaBrojaci[kava].text.toString().toInt()
        if (brojac > 1) {
            brojac -= 1
            listaBrojaci[kava].text = brojac.toString()
            listaPodMenii[((kava + 1) * 4 / 2) - 1].visibility = View.GONE
            listaDodaci2PodMeni[2 * (kava + 1) - 2].isChecked = false
            listaDodaci2PodMeni[2 * (kava + 1) - 1].isChecked = false
        }
        if (kava == 7) scrollView2.scrollTo(0, pomocni.bottom)
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


        closeAllPrevs()
        ukupnaCena += dodajNaRacun
        dodajNaRacun = 0
        buttonPorudzbina.text = ukupnaCena.toString()
    }


}




