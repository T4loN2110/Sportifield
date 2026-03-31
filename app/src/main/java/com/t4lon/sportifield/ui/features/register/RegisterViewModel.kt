package com.t4lon.sportifield.ui.features.register

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

class RegisterViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _state = MutableStateFlow(RegisterContract.State())
    val state: StateFlow<RegisterContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<RegisterContract.Effect>()
    val effect = _effect.asSharedFlow()

    private val registerSuccess = UiText.StringResource(R.string.register_msg_success)
    private val registerError = UiText.StringResource(R.string.register_msg_error)

    fun handleIntent(intent: RegisterContract.Intent) {
        when (intent) {
            is RegisterContract.Intent.EmailChanged -> {
                _state.update { it.copy(email = intent.email)}
            }
            is RegisterContract.Intent.PasswordChanged -> {
                _state.update { it.copy(password = intent.password)}
            }
            is RegisterContract.Intent.RegisterClicked -> register()
            is RegisterContract.Intent.LoginClicked -> {
                viewModelScope.launch {
                    _effect.emit(RegisterContract.Effect.NavigateToLogin)
                }
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val email = _state.value.email
            val password = _state.value.password

            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()

                if (result.user != null) {
                    _effect.emit(RegisterContract.Effect.ShowToast(registerSuccess))
                    _effect.emit(RegisterContract.Effect.NavigateToVerification)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, error = e.message)
                }
                _effect.emit(RegisterContract.Effect.ShowToast(registerError))
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}
