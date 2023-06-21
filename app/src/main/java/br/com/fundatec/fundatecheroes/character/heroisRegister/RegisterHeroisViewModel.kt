package br.com.fundatec.fundatecheroes.character.heroisRegister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern


class RegisterHeroisViewModel : ViewModel() {

    private val viewState = MutableLiveData<RegisterHeroisViewState>()
    val state: LiveData<RegisterHeroisViewState> = viewState
    fun validateInputs(name: String?, description: String?, age: String?, birth_date: String?) {
        var patternAge = Pattern.compile("^(?!0)\\d+$")
        var matcherAge = patternAge.matcher(age)

        var patternBirthDate = Pattern.compile("\\d{2}[-\\/\\.]\\d{2}[-\\/\\.]\\d{4}|\\d{8}")
        var matcherBirthDate = patternBirthDate.matcher(birth_date)

        viewState.value = RegisterHeroisViewState.ShowLoading

        if (name.isNullOrBlank() && description.isNullOrBlank() && age.toString()
                .isNullOrBlank() && birth_date.toString().isNullOrBlank()
        ) {
            viewState.value = RegisterHeroisViewState.ShowMessageError
            return
        }

        if (name.isNullOrBlank()) {
            viewState.value = RegisterHeroisViewState.ShowNameError
            return
        }
        if (description.isNullOrBlank()) {
            viewState.value = RegisterHeroisViewState.ShowDescriptionError
            return
        }
        if (!matcherAge.matches()) {
            viewState.value = RegisterHeroisViewState.ShowAgeError
            return
        }

        if (age.isNullOrBlank() || age.equals("0")) {
            viewState.value = RegisterHeroisViewState.ShowAgeError
            return
        }

        if (!matcherBirthDate.matches()) {
            viewState.value = RegisterHeroisViewState.ShowBirthDateError
            return
        }

        if (birth_date.isNullOrBlank()) {
            viewState.value = RegisterHeroisViewState.ShowNameError
            return
        }

        fetchLogin(name, description, age, birth_date)
    }

    private fun fetchLogin(name: String, description: String, age: String, birth_date: String) {
        viewState.value = RegisterHeroisViewState.ShowHomeScreen
    }
}


