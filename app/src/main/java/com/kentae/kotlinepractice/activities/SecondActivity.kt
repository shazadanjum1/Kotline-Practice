package com.kentae.kotlinepractice.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.kentae.kotlinepractice.R

class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val tv: TextView = findViewById(R.id.textView);

        val name: String? = getIntent().getStringExtra("name");

        tv.setText(name);

    }
}