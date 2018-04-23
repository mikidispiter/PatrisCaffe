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
lateinit var TurskaK: Kava
    lateinit var LatteK: Kava
    lateinit var CapucinoK: Kava
    lateinit var MocaK: Kava
    lateinit var MarocoK: Kava
    lateinit var InstantK: Kava
    lateinit var FrapucinoK: Kava
    lateinit var BrazilK: Kava

    var selected = false
    lateinit var kava: Kava
    var counterbieda = 1
    var brojac: Int = 1
    var dodajNaRacun: Int = 0
    var ukupnaCena: Int = 0


    class Kava(b: TextView, c: LinearLayout, d: LinearLayout, e: CheckBox, f: CheckBox, g: CheckBox, h: CheckBox, i: TextView) {
        var cena = b.text.toString().toInt()
        var prviPodMeniVidljivost: LinearLayout = c
        var druhiPodMeniVidljivost: LinearLayout = d
        var prviSlagCheckT: CheckBox = e
        var prviSecerCheckT: CheckBox = f
        var druhiSlagCheckT: CheckBox = g
        var druhiSecerCheckT: CheckBox = h
        var brojac: TextView = i


        fun uncheckDodaci() {
            prviSlagCheckT.isChecked = false
            druhiSlagCheckT.isChecked = false
            prviSecerCheckT.isChecked = false
            druhiSecerCheckT.isChecked = false
        }
    }



    //    Tu sa 1-raz inicializuju listi skrz modularnosti kodu (mali bi bit oznacenie z adekvatnima menami)
//    Abi sa primenili secke kavi potrebne je len poplnit listi aj linkuvat xml
    fun bieda() {
        if (counterbieda < 2) {
            counterbieda += 1

            TurskaK = Kava(cenaTurska, turska1, turska2, turskaSlag1, turskaSecer1, turskaSlag2, turskaSecer2, turskaBrojac)
            LatteK = Kava(cenaLatte, latte1, latte2, latteSlag1, latteSecer1, latteSlag2, latteSecer2, latteBrojac)
            CapucinoK = Kava(cenaCapucino, capucino1, capucino2, capucinoSlag1, capucinoSecer1, capucinoSlag2, capucinoSecer2, capucinoBrojac)
            MocaK = Kava(cenaMoca, moca1, moca2, mocaSlag1, mocaSecer1, mocaSlag2, mocaSecer2, mocaBrojac)
            MarocoK = Kava(cenaMaroco, maroco1, maroco2, marocoSlag1, marocoSecer1, marocoSlag2, marocoSecer2, marocoBrojac)
            InstantK = Kava(cenaInstant, instant1, instant2, instantSlag1, instantSecer1, instantSlag2, instantSecer2, instantBrojac)
            FrapucinoK = Kava(cenaFrapucino, frapucino1, frapucino2, frapucinoSlag1, frapucinoSecer1, frapucinoSlag2, frapucinoSecer2, frapucinoBrojac)
            BrazilK = Kava(cenaBrazil, brazil1, brazil2, brazilSlag1, brazilSecer1, brazilSlag2, brazilSecer2, brazilBrojac)
        }
    }


    //    Tento selektor bude robit zakazdim ked sa klikne edom z hlavnich layoutov kazdej vrste kave
    fun Kafa(view: View) {
        if (!selected) {
            kava = when (view) {
                Turska -> TurskaK
                Latte -> LatteK
                Capucino -> CapucinoK
                Moca -> MocaK
                Maroco -> MarocoK
                Instant -> InstantK
                Frapucino -> FrapucinoK
                Brazil -> BrazilK
                else -> FrapucinoK
            }
            managePrevs()
        } else closeAllPrevs()

    }


    //    Upravljania z prikazom pod-meniov (otvori odhovarajuci alebo zatvori secke u zavisnosti od selektovanej kave)
    fun managePrevs() {
        selected = !selected
        if (selected) {
            kava.prviPodMeniVidljivost.visibility = View.VISIBLE
        }
//        if (kava == BrazilK || kava == FrapucinoK) scrollView2.scrollTo(0, pomocni.bottom)
    }

    //    Zatvarania seckich pod-meniov
    fun closeAllPrevs() {
        brojac = 1

        kava.uncheckDodaci()

        kava.prviPodMeniVidljivost.visibility = View.GONE
        kava.druhiPodMeniVidljivost.visibility = View.GONE

        kava.brojac.text = brojac.toString()

        selected = false
    }


    //    Zveci broj kavov, otvori 2. pod meni, refreshuje brojac
    fun kafaVise(view: View) {
        brojac = kava.brojac.text.toString().toInt()
        if (brojac < 2) {
            brojac += 1
            kava.brojac.text = brojac.toString()
            kava.druhiPodMeniVidljivost.visibility = View.VISIBLE
        }
//        if (kava == BrazilK) scrollView2.scrollTo(0, pomocni.bottom)
    }

    //    Zmensi broj kavov, zatvori 2. pod meni, vipise novi brojac, *v buducnosti: pozve funkciju za zatvarania checkboxu
    fun kafaManje(view: View) {
        brojac = kava.brojac.text.toString().toInt()
        if (brojac > 1) {
            brojac -= 1
            kava.brojac.text = brojac.toString()
            kava.druhiPodMeniVidljivost.visibility = View.GONE
            kava.druhiSecerCheckT.isChecked = false
            kava.druhiSlagCheckT.isChecked = false
        }
//        if (kava == BrazilK) scrollView2.scrollTo(0, pomocni.bottom)
    }


    //    Dodaj kavi na racun (zateraz sa aktivira klikalim gombika Pogledaj Racun)
//    dostane vrednosti dodatkov (z checkboxov)
//    formira cenu shodne vibratej kave aj vibratima dodatkami, vipise cenu
//    restartuje layouti (selektuvana kava je 8(ani edna z kavov))
    fun dodaj(view: View) {
        if (selected) {

            var dodaci = ArrayList<Boolean>(4)
            var dodatak1: Boolean = kava.prviSlagCheckT.isChecked
            var dodatak2: Boolean = kava.prviSecerCheckT.isChecked
            var dodatak3: Boolean = kava.druhiSlagCheckT.isChecked
            var dodatak4: Boolean = kava.druhiSecerCheckT.isChecked
            dodaci.addAll(listOf(dodatak1, dodatak2, dodatak3, dodatak4))


            dodajNaRacun += brojac * kava.cena
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

}




