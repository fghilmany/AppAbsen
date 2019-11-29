package com.example.appabsen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.appabsen.cp.HomeActvity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = btn_login
        val edtId = edt_id
        val edtPass = edt_password
        val proggressBar = progress_bar
        proggressBar.visibility = View.INVISIBLE


        btnLogin.setOnClickListener {

            proggressBar.visibility = View.VISIBLE
            btnLogin.setText("")

            val id = edtId.text.toString()
            val pass = edtPass.text.toString()

            ref = FirebaseDatabase.getInstance().getReference("cp").child(id)
            ref.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(applicationContext, "database bermasalah", Toast.LENGTH_SHORT).show()

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()){
                        val password : String = dataSnapshot.child("password").getValue().toString()

                        if (pass.equals(password)){
                            startActivity<HomeActvity>(
                                "id" to id
                            )
                        }else{
                            Toast.makeText(applicationContext, " password salah + $pass + $id", Toast.LENGTH_SHORT).show()
                            proggressBar.visibility = View.INVISIBLE
                            btnLogin.setText("LOGIN")
                        }
                    }else{
                        Toast.makeText(applicationContext, "id  salah", Toast.LENGTH_SHORT).show()
                        proggressBar.visibility = View.INVISIBLE
                        btnLogin.setText("LOGIN")
                    }
                }

            })
        }
    }
}
