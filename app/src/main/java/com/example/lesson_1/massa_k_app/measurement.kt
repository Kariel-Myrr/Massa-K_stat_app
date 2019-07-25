package com.example.lesson_1.massa_k_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class measurement : AppCompatActivity() {


    lateinit var mDBHandler : DBHandler

    lateinit var mConfirmBtn : Button

    lateinit var textShipper : EditText
    lateinit var textReference : EditText
    lateinit var textContainerID :EditText
    lateinit var textLocation : EditText
    lateinit var textNote : EditText
    lateinit var textOperator : EditText
    lateinit var textSealID : EditText
    lateinit var textTare : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measurement)

        textShipper = findViewById(R.id.shipper_name_field)
        textReference = findViewById(R.id.reference_field)
        textContainerID = findViewById(R.id.container_id_field)
        textTare = findViewById(R.id.tare_field)
        textSealID = findViewById(R.id.seal_id_field)
        textLocation = findViewById(R.id.location_field)
        textOperator = findViewById(R.id.operator_field)
        textNote = findViewById(R.id.notes_field)


        mDBHandler = DBHandler(this)
    }
}
