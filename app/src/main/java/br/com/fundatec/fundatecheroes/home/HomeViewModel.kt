package br.com.fundatec.fundatecheroes.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fundatec.fundatecheroes.character.data.domain.CharacterUseCase
import br.com.fundatec.fundatecheroes.home.model.HomeViewState
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val useCase by lazy { CharacterUseCase() }

    private val viewState = MutableLiveData<HomeViewState>()
    val state: LiveData<HomeViewState> = viewState

    fun listCharacter() {
        viewModelScope.launch {
            viewState.value = HomeViewState.ShowLoading
            val list = useCase.getCharacters()

            if (list.isEmpty()) {
                viewState.value = HomeViewState.ShowEmptyList
            } else {
                viewState.value = HomeViewState.ShowHomeScreen(list)
            }
        }
    }
}