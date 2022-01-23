package com.example.kotlinroom_xirpl1_20_jordan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        supportActionBar?.hide()

        val intentButton: Button = findViewById(R.id.eventsButton)
        intentButton.setOnClickListener { viewDetail() }
    }
    private fun viewDetail() {
        val intent = Intent(this, RecyclerviewActivity::class.java)
        startActivity(intent)
    }
}