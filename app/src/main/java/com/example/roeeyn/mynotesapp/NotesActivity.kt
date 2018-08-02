package com.example.roeeyn.mynotesapp

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

        setupRecyclerview()
        fab.setOnClickListener {

            startActivity(Intent(this, NewNoteActivity::class.java))

        }

    }

    fun setupRecyclerview(){

        val layoutManager = LinearLayoutManager(this, VERTICAL, false)
        val adapter = NotesAdapter(getSampleNotes()){

            tostado(it.toString())

        }

        rv_main.adapter = adapter
        rv_main.layoutManager = layoutManager

    }

    fun getSampleNotes():List<Note> = listOf(
            Note("Ir a la BUAP", "Entrar a mis clases", "12-08-2018"),
            Note("Lavar la cocina", "Que quede limpia", "12-08-2020"),
            Note("Sacar al perro", "Que se canse", "19-08-2018")
    )

}
