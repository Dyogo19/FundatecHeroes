package br.com.fundatec.fundatecheroes.character.data.domain

import br.com.fundatec.fundatecheroes.character.data.repository.CharacterRepository
import br.com.fundatec.fundatecheroes.character.data.local.CharacterModel


class CharacterUseCase {

    private val repository by lazy { CharacterRepository() }

    suspend fun getCharacters(): List<CharacterModel>{
        return repository.getCharacters()
    }
}