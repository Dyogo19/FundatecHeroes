package br.com.fundatec.fundatecheroes.login.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.fundatec.fundatecheroes.login.LoginViewModel
import br.com.fundatec.fundatecheroes.login.model.LoginViewState
import io.mockk.spyk
import io.mockk.verifySequence
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    private val viewModel: LoginViewModel = LoginViewModel()
    private val stateObserver: Observer<LoginViewState> = spyk()

    @Test
    fun validate_inputs_when_null() {
        viewModel.validateInputs(null, null)

        assertEquals(viewModel.state.value, LoginViewState.ShowErrorMessage)
    }

    @Test
    fun validate_sequenceViewState_inputs_when_null() {
        prepareScenario()
        viewModel.validateInputs(null, null)

        verifySequence {
            stateObserver.onChanged(LoginViewState.ShowLoading)
            stateObserver.onChanged(LoginViewState.ShowErrorMessage)
        }
    }

    @Test
    fun validate_inputs_when_not_null() {
        viewModel.validateInputs("dionat@gmail.com", "123456")

        assertEquals(viewModel.state.value, LoginViewState.ShowHomeScreen)
    }

    private fun prepareScenario() {
        viewModel.state.observeForever(stateObserver)
    }

}