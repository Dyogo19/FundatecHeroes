package br.com.fundatec.fundatecheroes.profile.model

sealed class ProfileViewState {
    object ShowEmailErrorMessage : ProfileViewState()
    object ShowPasswordErrorMessage : ProfileViewState()
    object ShowErrorMessage : ProfileViewState()
    object ShowHomeScreen : ProfileViewState()
    object ShowLoading : ProfileViewState()



}