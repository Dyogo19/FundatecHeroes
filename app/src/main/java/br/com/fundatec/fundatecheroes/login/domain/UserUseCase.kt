package br.com.fundatec.fundatecheroes.login.domain

import br.com.fundatec.fundatecheroes.login.data.repository.LoginRepository

class UserUseCase {
    private val repository by lazy { LoginRepository() }

    suspend fun createUser(name:String,email: String, password: String): Boolean {
        return repository.createUser(name=name,email = email, password = password)
    }
}