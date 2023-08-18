package br.com.fundatec.fundatecheroes.character.data.repository

import android.util.Log
import br.com.fundatec.fundatecheroes.character.data.CharacterRequest
import br.com.fundatec.fundatecheroes.character.data.CharacterResponse
import br.com.fundatec.fundatecheroes.character.data.local.CharacterEntity
import br.com.fundatec.fundatecheroes.character.data.local.CharacterModel
import br.com.fundatec.fundatecheroes.database.FHDatabase
import br.com.fundatec.fundatecheroes.network.RetrofitNetworkClient
import br.com.fundatec.fundatecheroes.characterRegister.domain.CharacterRegisterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.*

class CharacterRepository {

    private val database: FHDatabase by lazy {
        FHDatabase.getInstance()
    }

    private val client = RetrofitNetworkClient
        .createNetworkClient()
        .create(CharacterService::class.java)


    suspend fun createCharacter(
        name: String,
        description: String,
        image: String, universeType: String,
        characterType: String,
        age: Int,
        birthday: Date
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.createCharacter(
                    CharacterRequest(
                        name,
                        description,
                        image,
                        universeType,
                        characterType,
                        age,
                        birthday
                    )
                )
                response.isSuccessful
            } catch (exception: Exception) {
                Log.e("createCharacter", exception.message.orEmpty())
                false
            }
        }
    }

    private fun CharacterResponse.characterResponseToEntity(): CharacterEntity {
        return CharacterEntity(
            name = name,
            description = description,
            image = image,
            universeType = universeType,
            characterType = characterType,
            age = age,
            birthday = birthday
        )
    }

    private fun CharacterResponse.characterResponseToModel(): CharacterModel {
        return CharacterModel(
            name = name, image = image
        )
    }

    private fun List<CharacterResponse>.convertToModelList(): List<CharacterModel> {
        return map { it.characterResponseToModel() }
    }

    fun getCharacters(): List<CharacterModel> {
        val response: Response<List<CharacterResponse>> =
            client.getCharacter(database.userDao().getId())

        if (response.isSuccessful) {
            val characterResponseList: List<CharacterResponse> = response.body() ?: emptyList()
            return characterResponseList.convertToModelList()
        } else {
            return emptyList()
        }
    }


    private fun mapCharacterModelToCharacterRegisterModel(characterModel: CharacterModel): CharacterRegisterModel {
        return CharacterRegisterModel(
            name = characterModel.name
        )
    }

    fun mapCharacterModelListToCharacterRegisterModelList(characterModelList: List<CharacterModel>): List<CharacterRegisterModel> {
        return characterModelList.map { mapCharacterModelToCharacterRegisterModel(it) }
    }
}

