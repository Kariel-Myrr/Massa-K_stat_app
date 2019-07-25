package com.example.lesson_1.massa_k_app

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main_bottom.*

class StartMenuActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {item->
        when(item.itemId){
            R.id.weight->{
                println("weight pressed")
                replaceFragment(FragmentWeightMain())
                return@OnNavigationItemSelectedListener true
            }
            R.id.history->{
                println("history pressed")
                replaceFragment(FragmentHistoryMain())
                return@OnNavigationItemSelectedListener true
            }
            R.id.settings->{
                println("settings pressed")
                replaceFragment(FragmentSettingsMain())
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bottom)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(FragmentWeightMain())
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}
