package com.example.kotlinroom_xirpl1_20_jordan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, Dashboard::class.java)
            startActivity(intent)
        }, 3000)
    }
}