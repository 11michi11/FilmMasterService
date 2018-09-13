package com.michi.filmmaster.connection

import com.michi.filmmaster.DetailedFilm
import com.michi.filmmaster.Film

interface WebService {

    fun getFilmsByTitle(title : String) : List<Film>

    fun getFilmInfo(id : String) : DetailedFilm

    fun addFilmToUsersWatchList(filmId : String, userId : String)
}