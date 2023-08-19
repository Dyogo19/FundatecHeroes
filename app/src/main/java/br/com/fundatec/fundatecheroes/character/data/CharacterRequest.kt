package br.com.fundatec.fundatecheroes.character.data

import java.util.*

data class CharacterRequest (
    val name: String,
    val description: String,
    val image: String,
    val universeType: String,
    val characterType: String,
    val age: Int,
    val birthday: String        )