package com.example.adminabsen

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.activity_home.*
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initFunc()
    }

    private fun initFunc() {
        var date = Date()
        val formater = SimpleDateFormat("HH:mm")
        val answer : String = formater.format(date)

        Toast.makeText(this,answer,Toast.LENGTH_SHORT).show()

        val qrEncoder = QRGEncoder(answer, null, QRGContents.Type.TEXT, 300)

        try {
            val bitmap = qrEncoder.encodeAsBitmap()
            iv_qr_code.setImageBitmap(bitmap)

        }catch (e:WriterException){

        }
    }
}
