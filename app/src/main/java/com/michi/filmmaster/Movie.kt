package com.michi.filmmaster

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity


class Movie : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_details_rated)
//
//        val requestOptions = RequestOptions()
//        requestOptions.placeholder(R.drawable.noposterfound)
//
//        val film = intent.getSerializableExtra(SearchList.KEY_FILM) as DetailedFilm
//
//
//        filmTitle.text = film.title
//        Glide.with(this).setDefaultRequestOptions(requestOptions).load(film.posterURL).into(filmPoster)
//        plot.text = film.plot
//        rating.text = film.imdbRating
//        year.text = film.realisedDate
//        time.text = film.runtime
//        director.text = film.director
//        actors.text = film.actors


        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavView_Bar)
        bottomNavBar.itemIconTintList = null
        bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_watch -> {
                    print("watch - film list")
                    val intent = Intent(applicationContext, Home::class.java)
                    startActivity(intent)
                }
                R.id.ic_favourite -> {
                    print("fav - film list")
                    val intent = Intent(applicationContext, SearchList::class.java)
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