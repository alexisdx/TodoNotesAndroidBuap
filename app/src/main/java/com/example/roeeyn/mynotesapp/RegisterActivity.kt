package com.example.roeeyn.mynotesapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbar)

        btn_new_register.setOnClickListener {

            val newUser = et_new_user.text.toString()
            val newPswd = et_new_pswd.text.toString()
            registerUser(newUser, newPswd)

        }

    }

    private fun registerUser(newUser:String, newPswd:String){

        val apiHelper = AppApiHelper()
        apiHelper.createUser(Models.CreateUserModel(newUser, newPswd))
                .subscribe({

                    if (it.success is String){
                        saveUserId(it.success)
                        tostado("Usuario creado ${it.success}")
                        finish()
                    }

                }){

                    tostado("Me rompí ${it.message}")

                }

    }

    private fun saveUserId(id:String){

        val prefs = getSharedPreferences("BUAP", Context.MODE_PRIVATE)
        prefs.edit().putString("USER_ID", id).apply()

    }

}
