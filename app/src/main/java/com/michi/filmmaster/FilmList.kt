package com.michi.filmmaster

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView

class FilmList : AppCompatActivity(){

    lateinit var list : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.film_list)

        val intent : Intent = intent

        val dupa = intent.getStringExtra("input")


        list = findViewById(R.id.list)

        var arr = arrayOf("1", "2", "3")
        arr[0] = dupa

        list.adapter = ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, arr)

    }

}