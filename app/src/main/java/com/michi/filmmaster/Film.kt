package com.michi.filmmaster

import java.io.Serializable

data class Film(val title: String,val type: String,val imdbID : String,val posterURL: String,val year: Int) : Serializable {



}
