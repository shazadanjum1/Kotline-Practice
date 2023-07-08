package com.kentae.kotlinepractice.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.kentae.kotlinepractice.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button);
        val tv: TextView = findViewById(R.id.textView);

        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                tv.setText("dff");
                val intent = Intent(this@MainActivity, SecondActivity::class.java);
                intent.putExtra("name", "abc");
                startActivity(intent);
                finish();

            }

        });

    }
}