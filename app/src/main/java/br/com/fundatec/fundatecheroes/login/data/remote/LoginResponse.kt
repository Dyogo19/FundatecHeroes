package br.com.fundatec.fundatecheroes.login.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val id: Int,
    val email: String,
    val password: String,
    val name: String
)