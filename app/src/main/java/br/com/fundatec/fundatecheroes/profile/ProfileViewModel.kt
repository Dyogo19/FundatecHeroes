package br.com.fundatec.fundatecheroes.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fundatec.fundatecheroes.login.domain.UserUseCase
import br.com.fundatec.fundatecheroes.profile.model.ProfileViewState
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class ProfileViewModel : ViewModel() {
    private val useCase by lazy { UserUseCase() }
    private val viewState = MutableLiveData<ProfileViewState>()
    val state: LiveData<ProfileViewState> = viewState


    fun validateInputsRegistrer(name: String?, email: String?, password: String?) {
        var patternEmail = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")


        var matcherEmail = patternEmail.matcher(email)





        viewState.value = ProfileViewState.ShowLoading


        if (name.isNullOrBlank()) {
            viewState.value = ProfileViewState.ShowNameError
            return
        }

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



        fetchLogin(name = name, email = email, password = password)

    }


    private fun fetchLogin(email: String, password: String, name: String) {
        viewModelScope.launch {
            val isSuccess = useCase.createUser(email = email, password = password, name = name)
            if (isSuccess) {
                viewState.value = ProfileViewState.ShowSuccesCreate
            } else {
                viewState.value = ProfileViewState.ShowErrorMessage
            }
        }

    }

}