package br.com.fundatec.fundatecheroes.main

import android.text.Editable
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fundatec.fundatecheroes.login.data.repository.LoginRepository

class SplashViewModel : ViewModel() {
    private val viewState = MutableLiveData<SplashViewState>()
    val state: LiveData<SplashViewState> = viewState


    fun validateUser(){
        
    }

}