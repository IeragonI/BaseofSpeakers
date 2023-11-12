package com.example.baseofspeakers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch

class Find : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find)

        val search:ImageView = findViewById(R.id.img_search)
        val edt_search:EditText = findViewById(R.id.edt_search)

        val list=findViewById<ListView>(R.id.list)
        val todos:MutableList<String> = mutableListOf()
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, todos)
        list.adapter=adapter

        search.setOnClickListener {
            var search_res:String = edt_search.text.toString()
            getData(adapter,search_res)
        }

        list.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, Svodka::class.java)
            startActivity(intent)
        }

    }

    var a:Int=0
    var str:Int = 0


    private fun getData(aaa:ArrayAdapter<String>, result:String) {
        lifecycleScope.launch {
            val client1 = getClient()
            val supabaseReponse1 = client1.postgrest["Speakers"].select()
            val data1 = supabaseReponse1.decodeList<Speakers>()
            var aw_name:List<String>
            var aw_surname:List<String>
            /*while(a < data1.size) {
                aw = listOf(data1[a].Name.toString())
                aaa.insert(aw.get(0), 0)
                a++
            }*/
            a = 0
            while (a < data1.size) {
                var fio:String = data1[a].Name.toString() +" "+ data1[a].Surname.toString()
                aw_name = listOf(data1[a].Name.toString())
                aw_surname = listOf(data1[a].Surname.toString())
                if (result == aw_name.get(0)){
                    aaa.insert(fio, 0)
                    str = a
                }else if(result == aw_surname.get(0)){
                    aaa.insert(fio, 0)
                    str = a
                }else if((result == aw_name.get(0) + " " + aw_surname.get(0)) or (result == aw_surname.get(0) + " " + aw_name.get(0))){
                    aaa.insert(fio, 0)
                    str = a
                }
                a++
            }

        }
    }

    private fun getClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://lxvlzpeedcjccaugjrhx.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imx4dmx6cGVlZGNqY2NhdWdqcmh4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTg2Njg3MDcsImV4cCI6MjAxNDI0NDcwN30.XUVf8JimDWRWXul6W-s7iSPiJ52_KH50G-B6mHeURlE")
        {
            install(Postgrest)
        }
    }

}