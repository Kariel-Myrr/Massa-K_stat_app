package com.example.lesson_1.massa_k_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    fun getParametres(): MutableList<Float> { // CMD_GET_MASSA
        var m : Float = 0.toFloat()
        var t : Float = 0.toFloat()
        var z : Float = 0.toFloat()

        // CMD_ACK_MASSA    success
        // CMD_ERROR        error
        var parametres : MutableList<Float> = arrayListOf()
        parametres.add(0, m)
        parametres.add(1,t)
        parametres.add(2,z)

        return parametres
    }

    fun setTare(t: Float): String { //CMD_SET_TARE
        var result : String = ""
        // device returns   CMD_NACK_TARE        if it is unable to set tare
        //                  CMD_ACK_SET_TARE     if success
        //                  CMD_ERROR            if error
        return result
    }

    fun setZero(t: Float): String { //CMD_SET_ZERO not all devices support that
        var result : String = ""
        // device returns   CMD_NACK       if it does not support that command
        //                  CMD_ACK_SET    if success
        //                  CMD_ERROR      if error
        return result
    }

    fun setUIparametres(){
        var par = getParametres()
        var Mass : Float
        var Tare : Float
        var Zero : Float
        Mass = par[0]
        Tare = par[1]
        Zero = par[2]

        mass.text = Mass.toString()
        tare1.text = Tare.toString()
        zero1.text = Zero.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var Mass : Float
        var Tare : Float
        var Zero : Float
        var par = getParametres()
        Mass = par[0]
        Tare = par[1]
        Zero = par[2]

        renew.setOnClickListener {
            setUIparametres()
        }


        set.setOnClickListener {
            var TareS : String = tare.editableText.toString()
            var ZeroS : String = zero.editableText.toString()
            Tare = TareS.toFloat()
            Zero = ZeroS.toFloat()
            setTare(Tare)
            setZero(Zero)

           setUIparametres()
        }

        set_default.setOnClickListener {
            Tare = 0.toFloat()
            Zero = 0.toFloat()
            setTare(Tare)
            setZero(Zero)

            setUIparametres()
        }
    }
}
