package com.michi.filmmaster

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.io.Serializable


class Home : AppCompatActivity() {

    lateinit var text: EditText
    lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        text = findViewById(R.id.input)
        button = findViewById(R.id.button)


    }

    fun filmList(view: View) {

        val intent : Intent = Intent(applicationContext, FilmList::class.java)

        startActivity(intent.putExtra("input",text.text.toString()))
    }

}
