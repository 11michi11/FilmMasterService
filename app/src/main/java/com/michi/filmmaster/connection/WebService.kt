package com.michi.filmmaster.connection

import android.support.v7.app.AppCompatActivity
import com.michi.filmmaster.Film

interface WebService {

    fun getFilmsByTitle(title : String) : List<Film>

    fun addFilmToUsersWatchList(filmId : String, userId : String)
}