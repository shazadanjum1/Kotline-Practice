package com.kentae.kotlinepractice.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.kentae.kotlinepractice.R
import com.kentae.kotlinepractice.database.SQLiteDB

class AddItemActivity : AppCompatActivity() {

    lateinit var db : SQLiteDB;
    var mode : String? = "add";
    var id : String = "";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        db = SQLiteDB(this@AddItemActivity, null);

        val backBtn: ImageView = findViewById(R.id.backBtn);
        val addBtn: Button = findViewById(R.id.addBtn);
        val nameET: EditText = findViewById(R.id.nameET);
        val detailET: EditText = findViewById(R.id.detailsET);

        try {
            mode = getIntent().getStringExtra("mode").toString();

            if (mode.equals("update")){
                id = getIntent().getStringExtra("id").toString();
                val name: String? = getIntent().getStringExtra("name");
                val detail: String? = getIntent().getStringExtra("detail");

                nameET.setText(name);
                detailET.setText(detail);

            }


        }catch ( e : Exception){
            e.stackTrace;
        }

        backBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@AddItemActivity, ItemActivity::class.java);
                startActivity(intent);
                finish();
            }
        });

        addBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val name: String = nameET.text.toString();
                val detail: String = detailET.text.toString();

                if (mode.equals("update")){
                    db.updateRow(id,name,detail);
                } else {
                    db.addName(name, detail);
                }

                val intent = Intent(this@AddItemActivity, ItemActivity::class.java);
                startActivity(intent);
                finish();
            }
        });

    }
}