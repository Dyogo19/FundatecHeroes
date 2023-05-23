package br.com.fundatec.fundatecheroes.registro.model

sealed class ProfileViewState {
    object ShowEmailErrorMessage : ProfileViewState()
    object ShowPasswordErrorMessage : ProfileViewState()
    object ShowErrorMessage : ProfileViewState()
    object ShowHomeScreen : ProfileViewState()
    object ShowLoading : ProfileViewState()



}