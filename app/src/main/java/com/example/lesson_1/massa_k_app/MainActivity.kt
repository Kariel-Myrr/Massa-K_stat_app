package com.example.lesson_1.massa_k_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /** Создаём объект java класса, через который будет проводиться включение bluetooth
    Передаём параметром текущую activity, чтобы можно было выовдить сообщения
    при включении, отключении bluetooth или в случае ошибок
    Объект сделан неизменяемым, чтобы избежать случайных ошибок*/
    private val bluetoothInstruments = BluetoothInstruments(this)

    /**TODO
     *  Разобраться с тем, за что отвечает параметр z и нужен ли он вообще
     */
    fun getParametres(): MutableList<Float> { // CMD_GET_MASSA
        var massNetto : Float = 0F  // Масса НЕТТО (масса продукта минус масса тары)
        var massTara : Float = 0F   // Масса пустой тары
        var z : Float = 0F          // ???

        // CMD_ACK_MASSA    success
        // CMD_ERROR        error

        var currentParametres : MutableList<Float> = arrayListOf() // Текущие параметры устройства
        currentParametres.add(0, massNetto)
        currentParametres.add(1, massTara)
        currentParametres.add(2, z)

        return currentParametres
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

        bluetoothInstruments.enableBT()     // Включаем bluetooth при запуске программы

        var Mass : Float
        var Tare : Float
        var Zero : Float
        var currentParameters = getParametres()
        Mass = currentParameters[0]
        Tare = currentParameters[1]
        Zero = currentParameters[2]

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

    // При закрытии программы сначала отключаем bluetooth, потом закрываем все широковещательные каналы
    override fun onDestroy() {
        bluetoothInstruments.disableBT()    // Отключаем bluetooth
        bluetoothInstruments.finish()       // Закрываем широковещательные каналы
        super.onDestroy()
    }
}
