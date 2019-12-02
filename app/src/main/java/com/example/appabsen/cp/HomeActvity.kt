package com.example.appabsen.cp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.appabsen.R
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_home_actvity.*
import java.text.SimpleDateFormat
import java.util.*

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

    //mengembalikan nilai dari hasil qr code di fragment home
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)


        //check if result is scan any qr
        if (result != null){
            if (result.contents == null){
                Toast.makeText(this, "QR tidak Valid", Toast.LENGTH_SHORT).show()
            }else{
                var date = Date()
                val formatter = SimpleDateFormat("HH:mm")
                val answer : String = formatter.format(date)
                Log.d("answer",answer)

                if (answer > "16.00"){
                    Toast.makeText(this,"Kamu Telat $answer" , Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Kamu Tepat Waktu $answer", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
