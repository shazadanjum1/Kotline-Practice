package com.kentae.kotlinepractice.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ezatpanah.roomdatabase_youtube.db.NoteEntity
import com.ezatpanah.roomdatabase_youtube.utils.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kentae.kotlinepractice.R
import com.kentae.kotlinepractice.adapters.ItemAdapter
import com.kentae.kotlinepractice.adapters.NoteAdapter
import com.kentae.kotlinepractice.database.NoteDatabase

class NoteActivity : AppCompatActivity() {

    // creating object of our database
    private val noteDB : NoteDatabase by lazy {
        Room.databaseBuilder(this,NoteDatabase::class.java, Constants.NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    lateinit var addBtn : FloatingActionButton;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        addBtn = findViewById<FloatingActionButton>(R.id.btnAddNote);

        val tvEmptyText = findViewById<TextView>(R.id.tvEmptyText);


        // getting the recyclerview by its id
        val rvNoteList = findViewById<RecyclerView>(R.id.rvNoteList);

        // this creates a vertical layout Manager
        rvNoteList.layoutManager = LinearLayoutManager(this);


        // if the database is not empty then show the list of data
        var data = noteDB.doa().getAllNotes()

        if(data.isNotEmpty()){
            rvNoteList.visibility= View.VISIBLE
            tvEmptyText.visibility= View.GONE

            // This will pass the ArrayList to our Adapter
            val adapter = NoteAdapter(data as MutableList<NoteEntity>, this@NoteActivity)

            // Setting the Adapter with the recyclerview
            rvNoteList.adapter = adapter

        }else{
            rvNoteList.visibility= View.GONE
            tvEmptyText.visibility= View.VISIBLE
        }

        addBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@NoteActivity, AddNoteActivity::class.java);
                intent.putExtra("mode", "add");
                startActivity(intent);
                finish();
            }
        });

    }
}