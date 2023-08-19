package br.com.fundatec.fundatecheroes.home.model

import br.com.fundatec.fundatecheroes.character.data.local.CharacterModel

sealed class HomeViewState {
    data class ShowHomeScreen(val list: List<CharacterModel>) : HomeViewState()

    object ShowEmptyList : HomeViewState()

    object ShowLoading : HomeViewState()
}