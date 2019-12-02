package com.example.appabsen.cp


import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.appabsen.R
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_home_fragmnent.*
import org.jetbrains.anko.support.v4.startActivityForResult
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
        //startActivityForResult(Intent(),1)

    }

    private fun iniFunc(){
        btn_scan.setOnClickListener {
            initScan()
        }
    }

    private fun initScan() {
        IntentIntegrator(activity).initiateScan()

    }

}
