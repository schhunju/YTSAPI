package com.example.api

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceholder:JsonPlaceholder=retrofit.create(JsonPlaceholder::class.java)

        val call:Call<List<IdClass>> = jsonPlaceholder.gMovies()

        call.enqueue(object : Callback<List<IdClass>>{
            override fun onFailure(call: Call<List<IdClass>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Failed",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<IdClass>>, response: Response<List<IdClass>>) {
                if(!response.isSuccessful){
                    tv1.setText("Code "+response.code())
                }
                else{
                    val post=response.body()

                    for (x in post!!)
                    {
                        var contents=""
                        contents +="Id= ${x.id} \n"
                        contents +="UserId= ${x.userId} \n"
                        contents +="Title= ${x.title} \n"
                        contents +="Body= ${x.body} \n"

                        tv1.append(contents)
                    }
                }
            }

        })
    }
}
