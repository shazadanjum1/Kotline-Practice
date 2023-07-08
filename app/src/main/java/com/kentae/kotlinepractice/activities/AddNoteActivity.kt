package com.kentae.kotlinepractice.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kentae.kotlinepractice.R
import com.kentae.kotlinepractice.database.NoteDatabase
import com.kentae.kotlinepractice.models.NoteEntity
import com.kentae.kotlinepractice.utils.Constants

class AddNoteActivity : AppCompatActivity() {

    // make an object of database to access the methods
    private val noteDB : NoteDatabase by lazy {
        Room.databaseBuilder(this,NoteDatabase::class.java, Constants.NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private lateinit var noteEntity: NoteEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val addBtn: FloatingActionButton = findViewById(R.id.btnSave);
        val edtTitle: EditText = findViewById(R.id.edtTitle);
        val descriptionET: EditText = findViewById(R.id.edtDesc);

        addBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val title: String = edtTitle.text.toString();
                val description: String = descriptionET.text.toString();

                noteEntity= NoteEntity(0,title, description)
                noteDB.doa().insertNote(noteEntity)
                val intent = Intent(this@AddNoteActivity, NoteActivity::class.java);
                startActivity(intent);
                finish();

            }
        });

    }

    override fun onBackPressed() {
        //super.onBackPressed()
        val intent = Intent(this@AddNoteActivity, NoteActivity::class.java);
        startActivity(intent);
        finish();
    }
}