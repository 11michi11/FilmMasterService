package com.michi.filmmaster

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import com.michi.filmmaster.connection.Service
import com.michi.filmmaster.connection.WebService

class FilmList : AppCompatActivity(){

    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmlist)


        listView = findViewById(R.id.listview)

        val list = mutableListOf<FilmView>()

        val service : WebService = Service()

        val films = service.getFilmsByTitle("man")

        list.addAll(films.map { FilmView(it.posterURL, it.title)})
        //list.add -> here we should add films to list


        val adapter = ListView(this, R.layout.film, list)

        listView.adapter = adapter
    }

}