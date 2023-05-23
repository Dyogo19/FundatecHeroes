package br.com.fundatec.fundatecheroes.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import br.com.fundatec.fundatecheroes.R

import br.com.fundatec.fundatecheroes.databinding.ActivityProfileBinding


import br.com.fundatec.fundatecheroes.profile.model.ProfileViewState
import br.com.fundatec.fundatecheroes.home.HomeActivity
import com.example.module.components.hide
import com.example.module.components.show
import com.google.android.material.snackbar.Snackbar

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initializeObserver()

        binding.registrar.setOnClickListener {
            viewModel.validateInputsRegistrer(
                password = binding.pwd.text.toString(),
                email = binding.email.text.toString(),



                )}

    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                ProfileViewState.ShowHomeScreen -> showHome()
                ProfileViewState.ShowErrorMessage -> showSnackError()
                ProfileViewState.ShowEmailErrorMessage -> showEmailError()
                ProfileViewState.ShowPasswordErrorMessage -> showPasswordError()
                ProfileViewState.ShowLoading -> showLoading()


            }
        }
    }


    private fun showLoading() {

        binding.pbLoading.show()
    }

    private fun showEmailError() {
        binding.pbLoading.hide()
        binding.email.error = getString(R.string.login_email_error_message)
    }

    private fun showPasswordError() {
        binding.pbLoading.hide()
        binding.pwd.error = getString(R.string.login_password_error_message)
    }

    private fun showSnackError() {
        binding.pbLoading.hide()
        Snackbar.make(binding.root, R.string.login_error_message, Snackbar.LENGTH_LONG).show()
    }

    private fun showHome() {
        binding.pbLoading.hide()
        val intent = Intent(this@ProfileActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}






