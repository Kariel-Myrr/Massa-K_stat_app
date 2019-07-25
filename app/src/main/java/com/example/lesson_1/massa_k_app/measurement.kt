package com.example.lesson_1.massa_k_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat

class measurement : AppCompatActivity() {


    lateinit var mDBHandler: DBHandler

    lateinit var mConfirmBtn: Button

    lateinit var textShipper: EditText
    lateinit var textReference: EditText
    lateinit var textContainerID: EditText
    lateinit var textLocation: EditText
    lateinit var textNote: EditText
    lateinit var textOperator: EditText
    lateinit var textSealID: EditText
    lateinit var textTare: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measurement)

        mConfirmBtn = findViewById(R.id.confirm_button)

        textShipper = findViewById(R.id.shipper_name_field)
        textReference = findViewById(R.id.reference_field)
        textContainerID = findViewById(R.id.container_id_field)
        textTare = findViewById(R.id.tare_field)
        textSealID = findViewById(R.id.seal_id_field)
        textLocation = findViewById(R.id.location_field)
        textOperator = findViewById(R.id.operator_field)
        textNote = findViewById(R.id.notes_field)


        mDBHandler = DBHandler(this)

        AddData()
    }


    fun AddData() {
        mConfirmBtn.setOnClickListener(View.OnClickListener {
            val shipper = textShipper.text.toString().trim()
            val reference = textReference.text.toString().trim()
            val container = textContainerID.text.toString().trim()
            val location = textLocation.text.toString().trim()
            val note = textNote.text.toString().trim()
            val operator = textOperator.text.toString().trim()
            val sealId = textSealID.text.toString().trim()
            val tare = textTare.text.toString().trim()

            if (TextUtils.isEmpty(shipper)) {
                textShipper.error = "Enter shipper"
                return@OnClickListener
            }

            if (TextUtils.isEmpty(reference)) {
                textReference.error = "Enter reference"
                return@OnClickListener
            }

            if (TextUtils.isEmpty(container)) {
                textContainerID.error = "Enter ID of container"
                return@OnClickListener
            }

            if (TextUtils.isEmpty(location)) {
                textLocation.error = "Enter location"
                return@OnClickListener
            }
            if (TextUtils.isEmpty(operator)) {
                textOperator.error = "Enter operator"
                return@OnClickListener
            }
            if (TextUtils.isEmpty(sealId)) {
                textSealID.error = "Enter seal Id"
                return@OnClickListener
            }
            if (TextUtils.isEmpty(tare)) {
                textTare.error = "Enter weight of tare"
                return@OnClickListener
            }

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss").toString()

            val isInserted =
                mDBHandler.addMeasurement(sdf, shipper, reference, container, tare, sealId, location, operator, note)


            if (isInserted == true) {
                Toast.makeText(applicationContext, "Data inserted ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Data could not be inserted ", Toast.LENGTH_SHORT).show()

            }

        })


    }
}
