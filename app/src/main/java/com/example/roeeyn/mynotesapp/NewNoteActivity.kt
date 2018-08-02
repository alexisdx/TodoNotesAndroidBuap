package com.example.roeeyn.mynotesapp

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_new_note.*
import kotlinx.android.synthetic.main.content_new_note.*

class NewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {

            val title = et_title.text.toString()
            val description = et_description.text.toString()
            val date = et_date.text.toString()

            createNewNote(title, description, date)

        }
    }

    fun createNewNote(title:String, description:String, date:String){

        val prefs = getSharedPreferences("BUAP", Context.MODE_PRIVATE)
        val userId = prefs.getString("USER_ID", "SIN_ID")

        val apiHelper = AppApiHelper()
        apiHelper.createNote(Models.NuevaNotaModel(title, description, date), userId)
                .subscribe({

                    tostado("Nota creada")
                    finish()

                }){

                    tostado("Me rompí")

                }

    }

}
