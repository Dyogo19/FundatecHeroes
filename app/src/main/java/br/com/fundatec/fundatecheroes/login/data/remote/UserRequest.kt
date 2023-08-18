package br.com.fundatec.fundatecheroes.login.data.remote

data class UserRequest (
    val name: String,
    val email: String,
    val password: String
)