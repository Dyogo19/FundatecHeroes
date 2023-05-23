package br.com.fundatec.fundatecheroes.registro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fundatec.fundatecheroes.registro.model.ProfileViewState
import java.util.regex.Pattern

class ProfileViewModel : ViewModel() {

    private val viewState = MutableLiveData<ProfileViewState>()
    val state: LiveData<ProfileViewState> = viewState


    fun validateInputsRegistrer(email: String?, password: String?) {
        var patternEmail = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")
        var patternSenha = Pattern.compile("^.{0,15}$")


        var matcherEmail = patternEmail.matcher(email)
        var matcherSenha = patternSenha.matcher(password)




        viewState.value = ProfileViewState.ShowLoading


        if (email.isNullOrBlank() && password.isNullOrBlank()) {
            viewState.value = ProfileViewState.ShowErrorMessage
            return
        }

        if (email.isNullOrBlank()) {
            viewState.value = ProfileViewState.ShowEmailErrorMessage
            return
        }

        if (password.isNullOrBlank()) {
            viewState.value = ProfileViewState.ShowPasswordErrorMessage
            return
        }


        if (!matcherEmail.matches()) {
            viewState.value = ProfileViewState.ShowEmailErrorMessage
            return
        }

        if (!matcherSenha.matches()) {
            viewState.value = ProfileViewState.ShowPasswordErrorMessage
            return
        }

        fetchRegister(email, password)

    }


    private fun fetchRegister(email: String, password: String) {
        viewState.value = ProfileViewState.ShowHomeScreen
    }


}