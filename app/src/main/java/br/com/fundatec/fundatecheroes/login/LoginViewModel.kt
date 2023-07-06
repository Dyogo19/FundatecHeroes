package br.com.fundatec.fundatecheroes.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fundatec.fundatecheroes.login.domain.LoginUseCase
import br.com.fundatec.fundatecheroes.login.model.LoginViewState
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private val viewState = MutableLiveData<LoginViewState>()
    val state: LiveData<LoginViewState> = viewState
    private val usecase by lazy { LoginUseCase() }


    fun validateInputs(email: String?, password: String?) {
        var patternEmail = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")
        var patternSenha = Pattern.compile("^.{4,}$")

        var matcherEmail = patternEmail.matcher(email)
        var matcherSenha = patternSenha.matcher(password)


        viewState.value = LoginViewState.ShowLoading


        if (email.isNullOrBlank() && password.isNullOrBlank()) {
            viewState.value = LoginViewState.ShowErrorMessage
            Log.e("Teste" , "passou no if")
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
        viewModelScope.launch {
            val isSuccess = usecase.login(email = email, password = password)
            if (isSuccess) {
                Log.e("Teste" , "passou no fetchLogin")
                viewState.value = LoginViewState.ShowHomeScreen
            } else {
                viewState.value = LoginViewState.ShowErrorMessage
            }
        }
    }

}