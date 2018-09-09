package com.michi.filmmaster.connection

import com.google.gson.Gson
import com.michi.filmmaster.Film
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import com.google.gson.reflect.TypeToken



fun main(args: Array<String>) {
    val service = Service()
    val films = service.getFilmsByTitle("man")
    println(films)
}


class Service : WebService {
    companion object {
        private const val SERVICE_URL = "http://localhost:8080/"
        val gson = Gson()
    }

    public override fun getFilmsByTitle(title: String): List<Film> {
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
