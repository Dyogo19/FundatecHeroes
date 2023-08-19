package br.com.fundatec.fundatecheroes.login.data.remote

data class UserRequest (
    val email: String,
    val password: String,
    val name: String
)