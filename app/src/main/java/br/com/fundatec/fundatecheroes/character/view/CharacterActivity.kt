package br.com.fundatec.fundatecheroes.character.view

import CharacterViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.fundatec.fundatecheroes.R
import br.com.fundatec.fundatecheroes.character.presentation.model.CharacterViewState
import br.com.fundatec.fundatecheroes.databinding.ActivityHeroRegisterBinding
import br.com.fundatec.fundatecheroes.home.HomeActivity
import com.example.module.components.hide
import com.example.module.components.show
import com.google.android.material.snackbar.Snackbar

class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroRegisterBinding
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeObserver()
        setupButtonClicks()
    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                is CharacterViewState.ShowLoading -> showLoading()
                is CharacterViewState.Success -> navigateNewCharacter()
                is CharacterViewState.ShowHomeScreen -> showHome()
                else -> {
                    Toast.makeText(this, "Erro desconhecido!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupButtonClicks() {
        binding.floatingButton.setOnClickListener {
            val name = binding.nameHero.text.toString()
            val description = binding.description.text.toString()
            val age = binding.age.text.toString()
            val birthday = binding.date.text.toString()

            viewModel.createCharacter(name, description, age, birthday)
        }
    }

    private fun navigateNewCharacter() {
        val intent = Intent(this@CharacterActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun showLoading() {
        binding.pbLoading.show()
    }

    private fun showAgeError() {
        binding.pbLoading.hide()
        binding.age.error = getString(R.string.register_age_error_message)
    }

    private fun showBirthDateError() {
        binding.pbLoading.hide()
        binding.date.error = getString(R.string.register_birthDate_error_message)
    }

    private fun showNameError() {
        binding.pbLoading.hide()
        binding.nameHero.error = getString(R.string.register_name_error_message)
    }

    private fun showHome() {
        binding.pbLoading.hide()
        finish()
    }

    private fun showSnackError() {
        binding.pbLoading.hide()
        Snackbar.make(binding.root, R.string.login_error2_message, Snackbar.LENGTH_LONG).show()
    }

    private fun showDescriptionError() {
        binding.pbLoading.hide()
        binding.description.error = getString(R.string.register_description_error_message)
    }
}