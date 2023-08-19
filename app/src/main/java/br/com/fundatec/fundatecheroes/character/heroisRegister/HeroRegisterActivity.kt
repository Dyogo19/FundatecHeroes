package br.com.fundatec.fundatecheroes.character.heroisRegister

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.fundatec.fundatecheroes.R
import br.com.fundatec.fundatecheroes.character.presentation.model.CharacterViewState
import br.com.fundatec.fundatecheroes.databinding.ActivityHeroRegisterBinding
import com.example.module.components.hide
import com.example.module.components.show
import com.google.android.material.snackbar.Snackbar

class HeroRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHeroRegisterBinding

    private val viewModel: RegisterHeroisViewModel by viewModels();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeObserver()

        validateData()
    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                CharacterViewState.ShowHomeScreen -> showHome()
                CharacterViewState.ShowLoading -> showLoading()
                CharacterViewState.ShowNameError -> showNameError()
                CharacterViewState.ShowMessageError -> showSnackError()
                CharacterViewState.ShowDescriptionError -> showDescriptionError()
                CharacterViewState.ShowAgeError -> showAgeError()
                CharacterViewState.ShowBirthDateError -> showBirthDateError()
                CharacterViewState.ShowImageError -> showImageError()
                else -> {}
            }
        }
    }

    private fun showLoading() {
        binding.pbLoading.show()
    }

    private fun showImageError() {
        binding.imgHero.error=getString(R.string.image_error_message)
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

    private fun validateData(){
        binding.floatingButton.setOnClickListener {
            viewModel.validateInputs(
                image = binding.imgHero.text.toString(),
                name = binding.nameHero.text.toString(),
                description = binding.description.text.toString(),
                universeType = binding.selectCompany.selectedItem.toString().uppercase(),
                characterType = binding.selectHero.selectedItem.toString().uppercase(),
                age = binding.age.text.toString(),
                birth_date = binding.date.text.toString()

            )
        }
    }

}




