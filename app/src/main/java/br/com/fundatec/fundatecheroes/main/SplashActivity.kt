package br.com.fundatec.fundatecheroes.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.fundatec.fundatecheroes.R
import br.com.fundatec.fundatecheroes.home.HomeActivity
import br.com.fundatec.fundatecheroes.login.LoginActivity
import com.example.module.components.hide


class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                SplashViewState.ShowHome -> showHome()
                SplashViewState.ShowLogin -> showLogin()
            }
        }
    }



    private fun showHome() {
        startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
        finish()
    }

    private fun showLogin() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }

}



