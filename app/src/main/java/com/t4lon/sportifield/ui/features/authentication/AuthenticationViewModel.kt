package com.t4lon.sportifield.ui.features.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthenticationViewModel : ViewModel() {
    private val _state = MutableStateFlow(AuthenticationContract.State())
    val state: StateFlow<AuthenticationContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<AuthenticationContract.Effect>()
    val effect = _effect.asSharedFlow()

    fun handleIntent(intent: AuthenticationContract.Intent) {
        when (intent) {
            is AuthenticationContract.Intent.EmailChanged -> {
                _state.update { it.copy(email = intent.email)}
            }
            is AuthenticationContract.Intent.PasswordChanged -> {
                _state.update { it.copy(password = intent.password)}
            }
            is AuthenticationContract.Intent.LoginClicked -> login()
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val currentState = _state.value
            val email = currentState.email
            val password = currentState.password
        }
    }
}