package com.example.baseofspeakers

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import java.net.URI

private lateinit var set_foto: ImageView
class Svodka : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_svodka)
        val txt_name:TextView = findViewById(R.id.txt_name)
        val txt_spec:TextView = findViewById(R.id.txt_spec)
        val txt_rab:TextView = findViewById(R.id.txt_rab)
        val txt_obsh:TextView = findViewById(R.id.txt_obsh)
        val txt_tel:TextView = findViewById(R.id.txt_tel)
        val txt_mail:TextView = findViewById(R.id.txt_mail)
        set_foto = findViewById(R.id.img_prof)

        var name:String = intent.getStringExtra("name").toString()
        var surname:String = intent.getStringExtra("surname").toString()
        var specialnost:String = intent.getStringExtra("prof").toString()
        var rabota:String = intent.getStringExtra("rab").toString()
        var obshee:String = intent.getStringExtra("obsh").toString()
        var telephone:String = intent.getStringExtra("tel").toString()
        var email:String = intent.getStringExtra("mail").toString()
        var puti: Uri = intent.getStringExtra("photo").toString().toUri()

        if (puti.toString() != "") {
            set_foto.setImageURI(puti)
            set_foto.setBackgroundResource(R.drawable.nol)
        }

        txt_name.setText(name + " " + surname)
        txt_rab.setText(rabota)
        txt_obsh.setText(obshee)
        txt_spec.setText(specialnost)
        txt_tel.setText(telephone)
        txt_mail.setText(email)

    }
}