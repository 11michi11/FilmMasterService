package com.michi.filmmaster

import com.michi.filmmaster.connection.Link
import java.io.Serializable


data class DetailedFilm(var title: String, var realisedDate: String, var runtime: String, var director: String, var actors: String, var plot: String, var posterURL: String, var imdbRating: String, var imdbID: String, val links : List<Link>) : Serializable
