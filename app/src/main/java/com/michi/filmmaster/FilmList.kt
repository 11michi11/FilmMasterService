package com.michi.filmmaster

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView

class FilmList : AppCompatActivity(){

    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmlist)


        listView = findViewById(R.id.listview)

        val list = mutableListOf<FilmView>()

        //list.add -> here we should add films to list
        list.add(FilmView(R.drawable.pf, "Pulp Fiction"))
        list.add(FilmView(R.drawable.lalaland, "La la land"))
        list.add(FilmView(R.drawable.whiplash, "Whiplash"))
        list.add(FilmView(R.drawable.bb, "Breaking Bad"))
        list.add(FilmView(R.drawable.pf, "Pulp Fiction"))
        list.add(FilmView(R.drawable.lalaland, "La la land"))
        list.add(FilmView(R.drawable.whiplash, "Whiplash"))
        list.add(FilmView(R.drawable.bb, "Breaking Bad"))


        val adapter = ListView(this, R.layout.film, list)

        listView.adapter = adapter
    }

}