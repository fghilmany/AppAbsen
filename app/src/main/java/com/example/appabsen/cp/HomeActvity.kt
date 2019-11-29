package com.example.appabsen.cp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.appabsen.R
import kotlinx.android.synthetic.main.activity_home_actvity.*

class HomeActvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_actvity)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.home -> {
                    loadHomeFragment(savedInstanceState)
                }
                R.id.amalan -> {
                    loadAmalanFragment(savedInstanceState)
                }
                R.id.profil -> {
                    loadProfilFragment(savedInstanceState)
                }
            }
            true
        }

        bottom_navigation.selectedItemId = R.id.home
    }

    private fun loadHomeFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, HomeFragmnent(), HomeFragmnent::class.java.simpleName)
                .commit()
        }
    }

    private fun loadAmalanFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, AmalanFragment(), AmalanFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadProfilFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, ProfilFragment(), ProfilFragment::class.java.simpleName)
                .commit()
        }
    }
}
