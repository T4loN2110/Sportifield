package com.t4lon.sportifield.ui.features.authentication

class AuthenticationContract {
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
    }

    sealed class Effect {
        data object NavigateToHome : Effect()
        data class ShowToast(val message: String) : Effect()
    }
}