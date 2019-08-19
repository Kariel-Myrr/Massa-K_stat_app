package com.example.lesson_1.massa_k_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main_bottom.*


class FragmentHistoryMain : Fragment() {


    lateinit var myDB : DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history_main, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myDB = DBHandler(getActivity()!!.applicationContext)


        val txt = ArrayList<TextView>()
        for (i in 0..9) {
            val txtItem = TextView(context)
            txt.add(txtItem)
        }

        val res = myDB.getAllData()
        var list = getView()!!.findViewById<ListView>(R.id.fragment_history_main_list)

        if (res.getCount() == 0) {
            //showMessage("Error ", "Nothing found")
            //return@OnClickListener

        } else {
            val buffer = StringBuffer()
            while (res.moveToNext()) {
                buffer.setLength(0)
                buffer.append(" \nId:" + res.getString(0) + "\n")
                buffer.append("Name: " + res.getString(1) + "\n")
                buffer.append("Profession: " + res.getString(2) + "\n")
                buffer.append("Salary: " + res.getString(3) + "\n\n")


                


                val txtItem = TextView(context)
                txtItem.text = buffer
                list.addFooterView(txtItem)
                txt.add(txtItem)

                Log.d("FLAG_TAG", buffer.toString())

            }
        }

    }
}
