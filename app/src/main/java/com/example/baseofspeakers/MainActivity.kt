package com.example.baseofspeakers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_create:Button = findViewById(R.id.btn_autorize)
        btn_create.setOnClickListener{
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
        val btn_smotr:Button = findViewById(R.id.btn_demo)
        btn_smotr.setOnClickListener {

        }
    }
}