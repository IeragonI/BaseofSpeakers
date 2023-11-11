package com.example.baseofspeakers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val edt_log:EditText = findViewById(R.id.edt_log)
        val edt_pass:EditText = findViewById(R.id.edt_pas)
        val btn_go: Button = findViewById(R.id.btn_go)
        btn_go.setOnClickListener {
            val log:String = edt_log.text.toString()
            val pass:String = edt_pass.text.toString()
            if((log == "admin") and (pass=="123")){
                val intent = Intent(this, Creating::class.java)
                startActivity(intent)
            }
        }
    }
}