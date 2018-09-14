package com.michi.filmmaster

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity


class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailfilm)


        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavView_Bar)
        bottomNavBar.itemIconTintList = null
        bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_watch -> {
                    print("watch - film list")
                    val intent = Intent(applicationContext, FilmList::class.java)
                    startActivity(intent)
                }
                R.id.ic_favourite -> {
                    print("fav - film list")
                    val intent = Intent(applicationContext, FilmList::class.java)
                    startActivity(intent)
                }
                R.id.ic_search -> {
                    print("search - home")
                    val intent = Intent(applicationContext, Home::class.java)
                    startActivity(intent)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }


    }


//    fun filmList(view: View) {
//

//        val intent : Intent = Intent(applicationContext, FilmList::class.java)
//
//        startActivity(intent.putExtra("input",text.text.toString()))
//    }

}
