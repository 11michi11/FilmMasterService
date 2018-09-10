package com.michi.filmmaster

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.michi.filmmaster.connection.Service
import com.michi.filmmaster.connection.WebService


class Home : AppCompatActivity() {


    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmlist)

        AsyncGetFilmsByTitle().execute("man")
    }

    fun handleList(films : List<Film>){
        listView = findViewById(R.id.listview)

        val list = mutableListOf<FilmView>()
        list.addAll(films.map { FilmView(it.posterURL, it.title)})

        val adapter = ListView(this, R.layout.film, list)
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



//    fun filmList(view: View) {
//
//        val intent : Intent = Intent(applicationContext, FilmList::class.java)
//
//        startActivity(intent.putExtra("input",text.text.toString()))
//    }

}
