package com.michi.filmmaster

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.michi.filmmaster.connection.Service
import com.michi.filmmaster.connection.WebService

class FilmList : AppCompatActivity(){

    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmlist)


        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavView_Bar)
        bottomNavBar.itemIconTintList = null
        bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_watch -> {
                    val intent = Intent(applicationContext, FilmList::class.java)
                    startActivity(intent)
                }
                R.id.ic_favourite -> {
                    val intent = Intent(applicationContext, FilmList::class.java)
                    startActivity(intent)
                }
                R.id.ic_search -> {
                    val intent = Intent(applicationContext, Home::class.java)
                    startActivity(intent)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

        AsyncGetFilmsByTitle().execute("man")
    }
    fun handleList(films : List<Film>){
        listView = findViewById(R.id.listview)

        val adapter = ListView(this, R.layout.film, films)
        listView.adapter = adapter
    }

    inner class AsyncGetFilmsByTitle : AsyncTask<String, Void, List<Film>>(){
        override fun doInBackground(vararg title: String?): List<Film> {
            val service : WebService = Service()
            return service.getFilmsByTitle(title[0].orEmpty())
        }

        override fun onPostExecute(result: List<Film>?) {
            super.onPostExecute(result)
            handleList(result.orEmpty())
        }
    }

}