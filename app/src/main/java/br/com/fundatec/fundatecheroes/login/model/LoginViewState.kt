package br.com.fundatec.fundatecheroes.login.model

sealed class LoginViewState {
    object ShowEmailErrorMessage : LoginViewState()
    object ShowPasswordErrorMessage : LoginViewState()
    object ShowErrorMessage : LoginViewState()
    object ShowHomeScreen : LoginViewState()
    object ShowLoading : LoginViewState()


}