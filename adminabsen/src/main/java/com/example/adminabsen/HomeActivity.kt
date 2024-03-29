package com.example.adminabsen

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.google.firebase.database.*
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.activity_home.*
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initFunc()
        initQr()
    }

    private fun initFunc() {

        // Update data tiap detik(waktu)
        var t =object: Thread(){
            override fun run() {
                while (!isInterrupted){
                    try {
                        sleep(10000) // 1000ms / milis = 1 s
                        runOnUiThread (object : Runnable{
                            override fun run() {

                                initQr()

                            }
                        })
                    }catch (e:Throwable){
                        e.printStackTrace()
                    }
                }
            }
        }
        //memulai update tiap detik
        t.start()

    }

    private fun initQr() {

        //set Waktu Local
        var date = Date()
        val formater = SimpleDateFormat("HH:mm:ss")
        val answer : String = formater.format(date)

        //Toast.makeText(applicationContext,answer,Toast.LENGTH_SHORT).show()

        //memasukan data waktu (answer) ke qr
        val qrEncoder = QRGEncoder(answer, null, QRGContents.Type.TEXT, 300)

        //konfersi qr ke bitmap
        try {
            val bitmap = qrEncoder.encodeAsBitmap()
            iv_qr_code.setImageBitmap(bitmap)

        }catch (e:WriterException){

        }

        ref = FirebaseDatabase.getInstance().getReference("qr_code")
        ref.child("qr_realtime").setValue(answer).addOnCompleteListener{
            Toast.makeText(applicationContext, "Qr Update", Toast.LENGTH_LONG).show()
        }


    }


}
