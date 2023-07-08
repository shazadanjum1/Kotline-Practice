package com.kentae.kotlinepractice.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.room.Room
import com.ezatpanah.roomdatabase_youtube.db.NoteEntity
import com.ezatpanah.roomdatabase_youtube.utils.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kentae.kotlinepractice.R
import com.kentae.kotlinepractice.database.NoteDatabase

class UpdateNoteActivity : AppCompatActivity() {
    // make an object of database to access the methods
    private val noteDB : NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, Constants.NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private lateinit var noteEntity: NoteEntity
    private var noteId = 0
    private var defaultTitle = ""
    private var defaultDesc = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

        val updateBtn: FloatingActionButton = findViewById(R.id.btnSave);
        val deleteBtn: FloatingActionButton = findViewById(R.id.btnDelete);

        val edtTitle: EditText = findViewById(R.id.edtTitle);
        val descriptionET: EditText = findViewById(R.id.edtDesc);

        intent.extras?.let {
            noteId = it.getInt(Constants.BUNDLE_NOTE_ID)
        }

        defaultTitle=noteDB.doa().getNote(noteId).noteTitle
        defaultDesc=noteDB.doa().getNote(noteId).noteDesc
        edtTitle.setText(defaultTitle)
        descriptionET.setText(defaultDesc)


        updateBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val title: String = edtTitle.text.toString();
                val description: String = descriptionET.text.toString();

                noteEntity= NoteEntity(noteId,title,description)
                noteDB.doa().updateNote(noteEntity)
                val intent = Intent(this@UpdateNoteActivity, NoteActivity::class.java);
                startActivity(intent);
                finish();
            }
        });

        deleteBtn.setOnClickListener {
            noteEntity= NoteEntity(noteId,defaultTitle,defaultDesc)
            noteDB.doa().deleteNote(noteEntity)
            val intent = Intent(this@UpdateNoteActivity, NoteActivity::class.java);
            startActivity(intent);
            finish();
        }

    }

    override fun onBackPressed() {
        //super.onBackPressed()
        val intent = Intent(this@UpdateNoteActivity, NoteActivity::class.java);
        startActivity(intent);
        finish();
    }
}