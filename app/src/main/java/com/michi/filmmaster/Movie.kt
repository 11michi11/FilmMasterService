package com.michi.filmmaster

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class Movie : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailfilm)

        val film = intent.getSerializableExtra(FilmList.KEY_FILM) as DetailedFilm

        val title = findViewById<TextView>(R.id.filmTitle)
        title.text = film.title

        val poster = findViewById<ImageView>(R.id.filmPoster)
        Glide.with(this).load(film.posterURL).into(poster)

        val plot= findViewById<TextView>(R.id.plot)
        plot.text = "Plot: ${film.plot}"

        val rating = findViewById<TextView>(R.id.rating)
        rating.text = film.imdbRating

        val year = findViewById<TextView>(R.id.year)
        year.text = "Release date: ${film.realisedDate}"

        val time = findViewById<TextView>(R.id.time)
        time.text = "Runtime: ${film.runtime}"

        val director = findViewById<TextView>(R.id.director)
        director.text = "Director: ${film.director}"

        val actors = findViewById<TextView>(R.id.actors)
        actors.text = "Actors: ${film.actors}"


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