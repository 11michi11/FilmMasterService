package com.michi.filmmaster.connection

import com.michi.filmmaster.DetailedFilm
import com.michi.filmmaster.Film

interface WebService {

    fun searchFilmsByTitle(title: String): List<Film>

    fun getFilmInfo(id: String): DetailedFilm

    fun addFilmToUsersWatchList(filmId: String, userId: String)
    suspend fun login(email: String, password: String): LogInResponse
}