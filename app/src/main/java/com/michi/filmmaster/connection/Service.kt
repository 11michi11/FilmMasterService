package com.michi.filmmaster.connection


import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.michi.filmmaster.DetailedFilm
import com.michi.filmmaster.Film
import khttp.get
import khttp.post
import khttp.structures.authorization.BasicAuthorization
import kotlinx.coroutines.runBlocking
import java.util.*

fun main() {
    val service = Service()
    runBlocking {
        service.login("registrationEmail", "encrypted")

    }

}

class Service : WebService {

    companion object {

//        VIA - change each time reconnected
        private const val SERVICE_URL = "http://10.152.206.147:8080/"
//        private const val SERVICE_URL = "http://10.0.2.2:8080/"
//        private const val SERVICE_URL = "http://localhost:8080/"
        lateinit var userId: String
        lateinit var userName: String
        lateinit var token: String
        val links = HashMap<String, String>()
        val gson = Gson()
    }

    //http://localhost:8080/oauth/token?grant_type=password&username=registrationEmail&password=encrypted
    override suspend fun login(email: String, password: String): LogInResponse {
        val params = mapOf("grant_type" to "password",
                "username" to email,
                "password" to password)

//        Log.d("login", "sending post")
        val response = post("${SERVICE_URL}oauth/token",
                params = params,
                auth = BasicAuthorization("my-trusted-client", "secret"))

//        Log.d("login", "post response ${response.text}")

        println(response.text)
        token = getToken(response.text)
        return if (response.statusCode == 200) {
            welcome()
            LogInResponse(true, "customerId")
        } else
            LogInResponse(false, "error")
    }

    private fun getToken(json: String): String {
        val jsonParser = JsonParser()
        val jsonObject = jsonParser.parse(json).asJsonObject
        return jsonObject.get("access_token").asString
    }

    fun welcome() {
        val response = get("${SERVICE_URL}welcome", params = mapOf("access_token" to token))
        println(response.text)

        val welcomeDTO = gson.fromJson(response.text, WelcomeDTO::class.java)
        val (name, id, linksList) = welcomeDTO
        userName = name
        userId = id.toString()
        links.putAll(linksList.map{it.rel to it.href}.toMap())
        print("$userName\n$userId\n$links")
    }


    override fun addFilmToUsersWatchList(filmId: String, userId: String) {

    }

    override fun getFilmInfo(id: String): DetailedFilm {
        val url = "${SERVICE_URL}films/$id/$userId"
        println(url)
        val response = get(url, params = mapOf("access_token" to token))
        val film: DetailedFilm = gson.fromJson(response.text, DetailedFilm::class.java)

        println(response)
        return film
    }

    override fun searchFilmsByTitle(title: String): List<Film> {
        val params = mapOf("title" to title, "access_token" to token)
        val url = "${SERVICE_URL}films/$userId/search/$title"
        return try {
            val response = get(url, params = params)
            val listType = object : TypeToken<LinkedList<Film>>() {}.type
            val films: List<Film> = gson.fromJson(response.text, listType)
            println(response)
            films
        } catch (e: Exception) {
            emptyList()
        }
    }

}

data class LogInResponse(val status: Boolean, val customerId: String)
data class WelcomeDTO(val name: String, val userId: Long, val links : List<Link>)
data class Link(val rel : String, val href : String)