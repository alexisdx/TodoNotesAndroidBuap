package com.example.roeeyn.mynotesapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout.VERTICAL
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.android.synthetic.main.content_notes.*

class NotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {

            startActivity(Intent(this, NewNoteActivity::class.java))

        }

    }

    override fun onResume() {
        getSampleNotes()
        super.onResume()
    }

    fun setupRecyclerview(notas:List<Note>){

        val layoutManager = LinearLayoutManager(this, VERTICAL, false)
        val adapter = NotesAdapter(notas){

            tostado(it.toString())

        }

        rv_main.adapter = adapter
        rv_main.layoutManager = layoutManager

    }

    fun getSampleNotes(){

        val prefs = getSharedPreferences("BUAP", Context.MODE_PRIVATE)
        val userId = prefs.getString("USER_ID", "SIN_ID")

        val apiHelper = AppApiHelper()
        apiHelper.getNotes(userId).subscribe({

            val notas = it.map { Note(it.title, it.description, it.date) }
            setupRecyclerview(notas)

        }){

            tostado("Me rompi")

        }

    }

}
