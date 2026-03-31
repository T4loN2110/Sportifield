package com.t4lon.sportifield.ui.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

import com.t4lon.sportifield.R
import com.t4lon.sportifield.util.UiText

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _state = MutableStateFlow(LoginContract.State())
    val state: StateFlow<LoginContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LoginContract.Effect>()
    val effect = _effect.asSharedFlow()

    private val loginSuccess = UiText.StringResource(R.string.login_msg_success)
    private val loginError = UiText.StringResource(R.string.login_msg_error)

    fun handleIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.EmailChanged -> {
                _state.update { it.copy(email = intent.email) }
            }
            is LoginContract.Intent.PasswordChanged -> {
                _state.update { it.copy(password = intent.password) }
            }
            is LoginContract.Intent.LoginClicked -> login()
            is LoginContract.Intent.RegisterClicked -> {
                viewModelScope.launch {
                    _effect.emit(LoginContract.Effect.NavigateToRegister)
                }
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val currentState = _state.value
            val email = currentState.email.trim()
            val password = currentState.password.trim()

            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()

                if (result.user != null) {
                    _effect.emit(LoginContract.Effect.ShowToast(loginSuccess))
                    _effect.emit(LoginContract.Effect.NavigateToHome)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, error = e.message ?: "Unknown error")
                }
                _effect.emit(LoginContract.Effect.ShowToast(loginError))
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}