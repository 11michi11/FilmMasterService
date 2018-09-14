package com.michi.filmmaster

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class Movie : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailfilm)

        val film = intent.getSerializableExtra(FilmList.KEY_FILM) as DetailedFilm

        val title = findViewById<TextView>(R.id.title)
        title.text = film.title

        val poster = findViewById<ImageView>(R.id.poster2)
        Glide.with(this).load(film.posterURL).into(poster)

        val content = findViewById<TextView>(R.id.content)
        content.text = film.plot

        val rating = findViewById<TextView>(R.id.rating)
        rating.text = film.imdbRating

        val year = findViewById<TextView>(R.id.year)
        year.text = film.realisedDate

        val time = findViewById<TextView>(R.id.time)
        time.text = film.runtime

        val director = findViewById<TextView>(R.id.director)
        director.text = film.director

        val actors = findViewById<TextView>(R.id.actors)
        actors.text = film.actors


        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavView_Bar)
        bottomNavBar.itemIconTintList = null
        bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_watch -> {
                    print("watch - film list")
                    val intent = Intent(applicationContext, FilmList::class.java)
                    startActivity(intent)
                }
                R.id.ic_favourite -> {
                    print("fav - film list")
                    val intent = Intent(applicationContext, FilmList::class.java)
                    startActivity(intent)
                }
                R.id.ic_search -> {
                    print("search - home")
                    val intent = Intent(applicationContext, Home::class.java)
                    startActivity(intent)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

    }
}