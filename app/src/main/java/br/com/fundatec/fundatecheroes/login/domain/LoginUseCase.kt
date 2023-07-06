package br.com.fundatec.fundatecheroes.login.domain

import br.com.fundatec.fundatecheroes.login.data.repository.LoginRepository

class LoginUseCase {
    private val repository by lazy { LoginRepository() }

    suspend fun login(email: String, password: String): Boolean {
        return repository.login(email = email, password = password)
    }
}