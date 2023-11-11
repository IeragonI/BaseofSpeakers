package com.example.baseofspeakers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch

class Creating : AppCompatActivity() {

    private lateinit var get_foto:ImageView
    companion object {
        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating)
        val edt_name:EditText = findViewById(R.id.edt_name)
        val edt_surname:EditText = findViewById(R.id.edt_surname)
        val edt_prof:EditText = findViewById(R.id.edt_prof)
        val edt_rab:EditText = findViewById(R.id.edt_rab)
        val edt_obsh:EditText = findViewById(R.id.edt_obsh)
        val edt_tel:EditText = findViewById(R.id.edt_tel)
        val edt_mail:EditText = findViewById(R.id.edt_mail)
        get_foto = findViewById(R.id.img_create_foto)
        val go:ImageView = findViewById(R.id.img_go)
        val edt_Name = ""
        go.setOnClickListener{
            var name = edt_name.text.toString()
            var surname = edt_surname.text.toString()
            var prof = edt_prof.text.toString()
            var rab = edt_rab.text.toString()
            var obsh = edt_obsh.text.toString()
            var tel = edt_tel.text.toString()
            var mail = edt_mail.text.toString()
            insertData(name, surname,prof,rab,obsh,tel,mail)
        }
        get_foto.setOnClickListener {
            pickImageGallary()
        }
    }
    private fun pickImageGallary() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            get_foto.setImageURI(data?.data)
        }
    }

    private fun insertData(name:String,surname:String,prof:String,rab:String,obsh:String,tel:String ,mail:String){
        lifecycleScope.launch{
            val client = getClient()
            var abc = Speakers(Name = name, Surname = surname,Prof =prof , Comp=rab , Info=obsh , contact_tel=tel , contact_mail=mail)
            client.postgrest["Speakers"].insert(value = abc)
        }
    }
}

private fun getClient(): SupabaseClient {
    return createSupabaseClient(
        supabaseUrl = "https://lxvlzpeedcjccaugjrhx.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imx4dmx6cGVlZGNqY2NhdWdqcmh4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTg2Njg3MDcsImV4cCI6MjAxNDI0NDcwN30.XUVf8JimDWRWXul6W-s7iSPiJ52_KH50G-B6mHeURlE"
    )

    {
        install(Postgrest)
    }
}
    @kotlinx.serialization.Serializable
    data class Speakers(
        val id: Int = 0,
        val Name: String = "",
        val Surname: String = "",
        val Prof: String = "",
        val Comp: String = "",
        val Info: String = "",
        val contact_tel: String = "",
        val contact_mail: String = "",
        val Foto: String = ""
    ) {
        override fun toString(): String {
            return "${id}${Name}${Surname}${Prof}${Comp}${Info}${contact_tel}${contact_mail}${Foto}"
        }
    }
