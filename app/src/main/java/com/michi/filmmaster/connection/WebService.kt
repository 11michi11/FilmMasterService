package com.michi.filmmaster.connection

import com.michi.filmmaster.Film

interface WebService {

    fun getFilmsByTitle(title : String) : List<Film>
}