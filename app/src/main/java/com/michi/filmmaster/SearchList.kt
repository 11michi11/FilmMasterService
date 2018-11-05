package com.michi.filmmaster

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.ListView
import com.michi.filmmaster.connection.Service
import com.michi.filmmaster.connection.WebService
import kotlinx.android.synthetic.main.activity_filmlist.*

class SearchList : AppCompatActivity() {

    lateinit var listView: ListView

    companion object {
        const val KEY_FILM = "film"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmlist)

        searchFilmTitle.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val content = s?.toString().orEmpty()
                if (content.length >= 3 && (content.length % 2 == 0 || content.length == 3))
                    AsyncGetFilmsByTitle().execute(content)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })


        bottomNavBar.itemIconTintList = null
        bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_watch -> {
                    val intent = Intent(applicationContext,Home::class.java)
                    startActivity(intent)
                }
                R.id.ic_favourite -> {
                    val intent = Intent(applicationContext, SearchList::class.java)
                    startActivity(intent)
                }
                R.id.ic_search -> {
                    val intent = Intent(applicationContext, Home::class.java)
                    startActivity(intent)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

//        AsyncGetFilmsByTitle().execute("man")
    }

    fun handleList(films: List<Film>) {
        val adapter = ListView(this, R.layout.film, films)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            AsyncGetFilmInfoByID().execute(films[position].imdbID)
        }
    }

    fun openDetailedFilmActivity(film: DetailedFilm) {
        startActivity(Intent(this, Movie::class.java).putExtra(KEY_FILM, film))
    }


    inner class AsyncGetFilmsByTitle : AsyncTask<String, Void, List<Film>>() {
        override fun doInBackground(vararg title: String?): List<Film> {
            val service: WebService = Service()
            return service.getFilmsByTitle(title[0].orEmpty())
        }

        override fun onPostExecute(result: List<Film>?) {
            super.onPostExecute(result)
            handleList(result.orEmpty())
        }
    }

    inner class AsyncGetFilmInfoByID : AsyncTask<String, Void, DetailedFilm>() {
        override fun doInBackground(vararg id: String?): DetailedFilm {
            val service: WebService = Service()
            return service.getFilmInfo(id[0].orEmpty())
        }

        override fun onPostExecute(result: DetailedFilm) {
            super.onPostExecute(result)
            openDetailedFilmActivity(result)
        }
    }

}