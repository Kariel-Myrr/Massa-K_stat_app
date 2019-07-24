package com.example.lesson_1.massa_k_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.weight_main.*

class MainActivity : AppCompatActivity() {


    fun displayWeight(demo : View){
        val intent = Intent(this@MainActivity, measurement::class.java)
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weight_main)
        weight_display.setOnClickListener(::displayWeight)
    }


}
