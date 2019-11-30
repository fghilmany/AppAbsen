package com.example.appabsen.cp


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.appabsen.R
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_home_fragmnent.*
import java.text.SimpleDateFormat
import java.util.*

class HomeFragmnent : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fragmnent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniFunc()

    }

    private fun iniFunc(){
        btn_scan.setOnClickListener {
            initScan()
        }
    }

    private fun initScan() {
        IntentIntegrator(this.activity).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)

        //check if result is scan any qr
        if (result != null){
            if (result.contents == null){
                sequenceOf(Toast.makeText(activity, "QR tidak Valid", Toast.LENGTH_SHORT))
            }else{
                var date = Date()
                val formatter = SimpleDateFormat("HH:mm")
                val answer : String = formatter.format(date)

                if (answer > "16.00"){
                    Toast.makeText(activity,"Kamu Telat ${result.contents.toString()}",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(activity,"Kamu Tepat Waktu $result",Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}
