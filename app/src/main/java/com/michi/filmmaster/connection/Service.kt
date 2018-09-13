package com.michi.filmmaster.connection

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.michi.filmmaster.DetailedFilm
import com.michi.filmmaster.Film
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


fun main(args: Array<String>) {
    val service = Service()
    val films = service.getFilmInfo("tt1300854")
    println(films)
}


class Service : WebService {
    companion object {

        private const val SERVICE_URL = "http://10.0.2.2:8080/"
        val gson = Gson()
    }
    override fun addFilmToUsersWatchList(filmId: String, userId: String) {

    }

    override fun getFilmInfo(id: String): DetailedFilm {
        val url = SERVICE_URL + "filmInfo?id=$id"
        val response = sendGet(url)
        val film :DetailedFilm = gson.fromJson(response, DetailedFilm::class.java)

        println(response)
        return film
    }

    override fun getFilmsByTitle(title: String): List<Film> {
        val name = "?name=$title"
        val url = SERVICE_URL + "filmName$name"
        val response = sendGet(url)
        val listType = object : TypeToken<ArrayList<Film>>() {}.type
        val films : List<Film> = gson.fromJson(response, listType)

        println(response)
        return films
    }



    private fun sendGet(url: String) : String{
        val obj = URL(url)

        with(obj.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "GET"

            println("\nSending 'GET' request to URL : $url")
            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                return response.toString()
            }
        }
    }

}
