package com.example.empresas_android

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {




    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtEmail:EditText? = findViewById<EditText>(R.id.edtEmail)
        val edtPass:EditText? = findViewById<EditText>(R.id.edtPassword)
        val btnLogin:Button? = findViewById<Button>(R.id.btnLogin)
        val txtResult:TextView? = findViewById<TextView>(R.id.txtResult)
        var text:String? = ""



        btnLogin?.setOnClickListener {
            startActivity(Intent(this, ListingEnterprisesActivity::class.java))
        }
    }
}
