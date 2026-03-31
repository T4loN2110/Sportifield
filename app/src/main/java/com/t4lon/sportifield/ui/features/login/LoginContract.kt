package com.t4lon.sportifield.ui.features.login

import com.t4lon.sportifield.util.UiText

class LoginContract {
    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val isAuthenticated: Boolean = false,
        val email: String = "",
        val password: String = "",
    )

    sealed class Intent {
        data class EmailChanged(val email: String) : Intent()
        data class PasswordChanged(val password: String) : Intent()
        data object LoginClicked : Intent()
        data object RegisterClicked : Intent()
    }

    sealed class Effect {
        data object NavigateToHome : Effect()
        data object NavigateToRegister : Effect()
        data class ShowToast(val message: UiText) : Effect()
    }
}