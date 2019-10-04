package com.apontipip.concentrationcco

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.EditText
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.lang.Float.parseFloat

class MainActivity(internal var numberScale1: EditText? = null, internal var numberScale2: EditText? = null) : AppCompatActivity(), View.OnClickListener{
    internal lateinit var numberScale3: EditText
    internal lateinit var resultFieldCaust: TextView// текстовое поле для вывода результата каустика
    internal lateinit var resultFieldCarb: TextView // текстовое поле для вывода результата
    internal lateinit var resultFieldOx: TextView // текстовое поле для вывода результата
    internal lateinit var calculateRes: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // получаем все поля по id из activity_main.xml
        resultFieldCaust = findViewById<View>(R.id.outputCaustRes) as TextView
        resultFieldCarb = findViewById<View>(R.id.outputCarbonRes) as TextView
        resultFieldOx = findViewById<View>(R.id.outputOxonyRes) as TextView

        numberScale1 = findViewById<View>(R.id.scale1) as EditText
        numberScale2 = findViewById<View>(R.id.scale2) as EditText
        numberScale3 = findViewById<View>(R.id.scale3) as EditText

        calculateRes = findViewById<View>(R.id.calculate) as Button

        calculateRes.setOnClickListener(this)
    }


    override fun onClick(view: View) {

        var scale1 = 0f
        var scale2 = 0f
        var scale3 = 0f
        var caustic = 0f
        var carbonats = 0f
        var oxony = 0f
        var kislota = 0f




        // читаем EditText и заполняем переменные числами
        if (numberScale1?.text.toString().trim { it <= ' ' }.length > 0) {
            scale1 = parseFloat(numberScale1?.text.toString())
        }
        if (numberScale2?.text.toString().trim { it <= ' ' }.length > 0) {
            scale2 = parseFloat(numberScale2?.text.toString())
        }
        if (numberScale3.text.toString().trim { it <= ' ' }.isNotEmpty()) {
            scale3 = parseFloat(numberScale3.text.toString())
        }

        when (view.id) {
            R.id.calculate -> {
                caustic = ((scale1 * 2 - scale2) * 0.08).toFloat()
                carbonats = ((scale2 - scale1) * 0.212).toFloat()
                oxony = scale3 * 38 / 1500
            }
            else -> {
            }
        }
        resultFieldCaust.text = "" + caustic
        resultFieldCarb.text = "" + carbonats
        resultFieldOx.text = "" + oxony
    }

    override fun onBackPressed() {
        if (back_pressed + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(baseContext,
                    getString(R.string.back_pressed), Toast.LENGTH_SHORT).show()
        }
        back_pressed = System.currentTimeMillis()
    }

    companion object {
        private var back_pressed: Long = 0
    }

}



