package br.com.fundatec.fundatecheroes.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fundatec.fundatecheroes.login.model.LoginViewState
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private val viewState = MutableLiveData<LoginViewState>()
    val state: LiveData<LoginViewState> = viewState


    fun validateInputs(email: String?, password: String?) {
        var patternEmail = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")
        var patternSenha = Pattern.compile("^.{4,}$")

        var matcherEmail = patternEmail.matcher(email)
        var matcherSenha = patternSenha.matcher(password)


        viewState.value = LoginViewState.ShowLoading


        if (email.isNullOrBlank() && password.isNullOrBlank()) {
            viewState.value = LoginViewState.ShowErrorMessage
            return
        }

        if (email.isNullOrBlank()) {
            viewState.value = LoginViewState.ShowEmailErrorMessage
            return
        }

        if (password.isNullOrBlank()) {
            viewState.value = LoginViewState.ShowPasswordErrorMessage
            return
        }
        if (!matcherEmail.matches()) {
            viewState.value = LoginViewState.ShowEmailErrorMessage
            return
        }

        if (!matcherSenha.matches()) {
            viewState.value = LoginViewState.ShowPasswordErrorMessage
            return
        }

        fetchLogin(email, password)

    }


    private fun fetchLogin(email: String, password: String) {
        viewState.value = LoginViewState.ShowHomeScreen
    }

}