package br.com.fundatec.fundatecheroes.main


sealed class SplashViewState{

    object ShowHome : SplashViewState()

    object ShowLogin : SplashViewState()
}