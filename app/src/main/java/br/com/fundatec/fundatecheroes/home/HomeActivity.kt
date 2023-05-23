package br.com.fundatec.fundatecheroes.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fundatec.fundatecheroes.HomeViewPager
import br.com.fundatec.fundatecheroes.heroisRegister.RegisterHeroisActivity
import br.com.fundatec.fundatecheroes.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = HomeViewPager(supportFragmentManager)
        binding.homeViewPager.adapter = adapter
        binding.homeTabLayout.setupWithViewPager(binding.homeViewPager)

        binding.btHerois.setOnClickListener {
            showHeroesRegister()
        }
    }

    private fun showHeroesRegister() {
        val intent = Intent(this@HomeActivity, RegisterHeroisActivity::class.java)
        startActivity(intent)
        finish()
    }
}

