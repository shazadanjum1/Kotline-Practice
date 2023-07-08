package com.kentae.kotlinepractice.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kentae.kotlinepractice.adapters.ItemAdapter
import com.kentae.kotlinepractice.R
import com.kentae.kotlinepractice.database.SQLiteDB

class ItemActivity : AppCompatActivity() {
    lateinit var db : SQLiteDB;
    lateinit var addBtn : FloatingActionButton;

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item);

        db = SQLiteDB(this@ItemActivity, null);

        addBtn = findViewById<FloatingActionButton>(R.id.addBtn);

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView);

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this);

        // ArrayList of class ItemsViewModel
        //var data = ArrayList<ItemsModel>();
//        for (i in 1..10) {
//            data.add(ItemsModel("id","name " + i, "detail " + i));
//        }

        var data = db.getAllRecords()!!;

        // This will pass the ArrayList to our Adapter
        val adapter = ItemAdapter(data, this@ItemActivity)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        addBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@ItemActivity, AddItemActivity::class.java);
                intent.putExtra("mode", "add");
                startActivity(intent);
                finish();
            }
        });
    }
}