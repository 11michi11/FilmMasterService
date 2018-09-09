package com.michi.filmmaster

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import java.io.Serializable


class Home : AppCompatActivity() {


    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmlist)


        listView = findViewById(R.id.listview)

        val list = mutableListOf<FilmView>()

        //list.add -> here we should add films to list
        list.add(FilmView(R.drawable.eye, "EyeExample"))

        val adapter = ListView(this, R.layout.film, list)

        listView.adapter = adapter
    }

//    fun filmList(view: View) {
//
//        val intent : Intent = Intent(applicationContext, FilmList::class.java)
//
//        startActivity(intent.putExtra("input",text.text.toString()))
//    }

}
