package br.com.fundatec.fundatecheroes.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import br.com.fundatec.fundatecheroes.R
import br.com.fundatec.fundatecheroes.login.LoginActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("StringFormatInvalid", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }



}

