package br.com.fundatec.fundatecheroes.character.heroisRegister

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import br.com.fundatec.fundatecheroes.R
import br.com.fundatec.fundatecheroes.databinding.ActivityHeroRegisterBinding
import br.com.fundatec.fundatecheroes.home.HomeActivity
import com.example.module.components.hide
import com.example.module.components.show
import com.google.android.material.snackbar.Snackbar

class RegisterHeroisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroRegisterBinding

    private val viewModel: RegisterHeroisViewModel by viewModels();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeObserver()

        binding.floatingButton.setOnClickListener {
            viewModel.validateInputs(
                name = binding.nameHero.text.toString(),
                description = binding.description.text.toString(),
                age = binding.age.text.toString(),
                birth_date = binding.date.text.toString()
            )
        }
    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                RegisterHeroisViewState.ShowHomeScreen -> showHome()
                RegisterHeroisViewState.ShowLoading -> showLoading()
                RegisterHeroisViewState.ShowNameError -> showNameError()
                RegisterHeroisViewState.ShowMessageError -> showSnackError()
                RegisterHeroisViewState.ShowDescriptionError -> showDescriptionError()
                RegisterHeroisViewState.ShowAgeError -> showAgeError()
                RegisterHeroisViewState.ShowBirthDateError -> showBirthDateError()
            }
        }
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
        val intent = Intent(this@RegisterHeroisActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showSnackError() {
        binding.pbLoading.hide()
        Snackbar.make(binding.root, R.string.login_error_message_hero_register, Snackbar.LENGTH_LONG).show()
    }

    private fun showDescriptionError() {
        binding.pbLoading.hide()
        binding.description.error = getString(R.string.register_description_error_message)
    }
}




