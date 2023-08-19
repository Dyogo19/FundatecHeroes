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
        characterRequest: CharacterRequest
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.createCharacter(
                    database.userDao().getId(),
                    CharacterRequest(
                        characterRequest.name,
                        characterRequest.description,
                        characterRequest.image,
                        characterRequest.universeType,
                        characterRequest.characterType,
                        characterRequest.age,
                        characterRequest.birthday
                    )
                )
                response.isSuccessful
            } catch (exception: Exception) {
                Log.e("createCharacter", exception.message.orEmpty())
                false
            }
        }
    }

    suspend fun getCharacters(): List<CharacterModel> {
        return withContext(Dispatchers.IO) {
            Log.e("Log" , database.userDao().getId().toString())
            val response: Response<List<CharacterResponse>> =
                client.getCharacter(database.userDao().getId())

            if (response.isSuccessful) {
                val characterResponseList: List<CharacterResponse> = response.body() ?: emptyList()
                characterResponseList.convertToModelList()
            } else {
                emptyList()
            }
        }
    }

    private fun List<CharacterResponse>.convertToModelList(): List<CharacterModel> {
        return map { it.characterResponseToModel() }
    }

    private fun CharacterResponse.characterResponseToModel(): CharacterModel {
        return CharacterModel(
            name = name, image = image
        )
    }

}
