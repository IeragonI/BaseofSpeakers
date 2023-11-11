package com.example.baseofspeakers

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
        }}

        var a:Int=0
        private fun getData(aaa:ArrayAdapter<String>, result:String){
            lifecycleScope.launch{
                val client = getClient()
                val supabaseReponse = client.postgrest["Speakers"].select()
                val data = supabaseReponse.decodeList<Speakers>()
                var sovp:String = ""
                a = data.size
                var i:Int = 0
                while (result != data[i].Name.toString()){
                    sovp = data[i].Name.toString()
                    i++
                }
                aaa.insert(sovp,0)
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
