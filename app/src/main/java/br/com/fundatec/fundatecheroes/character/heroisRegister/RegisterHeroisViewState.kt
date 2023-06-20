package br.com.fundatec.fundatecheroes.character.heroisRegister

sealed class RegisterHeroisViewState {

    object ShowHomeScreen : RegisterHeroisViewState()
    object ShowLoading : RegisterHeroisViewState()
    object ShowNameError : RegisterHeroisViewState()
    object ShowDescriptionError : RegisterHeroisViewState()
    object ShowMessageError : RegisterHeroisViewState()
    object ShowAgeError : RegisterHeroisViewState()
    object ShowBirthDateError : RegisterHeroisViewState()
}


