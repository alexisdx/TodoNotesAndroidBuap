package com.example.roeeyn.mynotesapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "onCrate", Toast.LENGTH_SHORT).show()

        btn_login.setOnClickListener {

            val usuario = et_user.text.toString()
            val pswd = et_pswd.text.toString()

            login(usuario, pswd)

        }

        btn_register.setOnClickListener { gotoRegister() }

    }

    private fun login(user:String, pswd:String){

        val apiHelper = AppApiHelper()
        apiHelper.login(Models.LoginModel(user, pswd))
                .subscribe({

                    if (it.success is String){
                        tostado("${it.success}")
                    } else {
                        tostado("Malas credenciales")
                    }

                }){

                    tostado("Me rompí ${it.message}")

                }

    }

    private fun gotoRegister() =
            startActivity(Intent(this, RegisterActivity::class.java))



    private fun goToNotesActivity(){

        val intent = Intent(this, NotesActivity::class.java)
        startActivity(intent)

    }

}

