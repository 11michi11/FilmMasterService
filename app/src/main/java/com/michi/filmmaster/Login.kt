package com.michi.filmmaster

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.michi.filmmaster.connection.LogInResponse
import com.michi.filmmaster.connection.Service
import com.michi.filmmaster.connection.WebService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Login : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginAction(v: View) {
        val login = loginField.text.toString()
        val password = passwordField.text.toString()

        GlobalScope.launch {
            Log.d("login", "login")
            val service: WebService = Service()
            val response = service.login(login, password)
            Log.d("login", response.status.toString())
            if (response.status)
                openHomeActivity()
        }

    }


    private fun openHomeActivity() {
        startActivity(Intent(applicationContext, Home::class.java))
    }

}