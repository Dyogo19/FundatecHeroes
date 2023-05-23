package br.com.fundatec.fundatecheroes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.fundatec.fundatecheroes.login.LoginViewModel
import br.com.fundatec.fundatecheroes.login.model.LoginViewState
import io.mockk.spyk
import io.mockk.verifySequence
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class LoginViewModelTest2 {
    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    private val viewModel = LoginViewModel()
    private val stateObserver: Observer<LoginViewState> = spyk()

    @Test
    fun validateViewState_returnShowErrorEmptyFields() {
        prepareScenario()
        viewModel.validateInputs(null, null)

        assertEquals(viewModel.state.value, LoginViewState.ShowLoading)
    }

    @Test
    fun callLogin_verifyIsCalledLoginDataSource()  {
        prepareScenario()
        viewModel.validateInputs("email", "password")

//        assertEquals(viewModel.state.value, LoginViewState.ShowHomeScreen)
        verifySequence {
            stateObserver.onChanged(LoginViewState.ShowLoading)
            stateObserver.onChanged(LoginViewState.ShowHomeScreen)
        }
    }

    private fun prepareScenario() {
        viewModel.state.observeForever(stateObserver)
    }
}