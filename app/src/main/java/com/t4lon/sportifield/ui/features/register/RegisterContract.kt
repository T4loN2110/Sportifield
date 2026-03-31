package com.t4lon.sportifield.ui.features.register

import com.t4lon.sportifield.util.UiText

class RegisterContract {
    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val email: String = "",
        val password: String = "",
        val token: String? = null
    )

    sealed class Intent {
        data class EmailChanged(val email: String) : Intent()
        data class PasswordChanged(val password: String) : Intent()
        data object RegisterClicked : Intent()
        data object LoginClicked : Intent()
    }

    sealed class Effect {
        data object NavigateToVerification : Effect()
        data object NavigateToLogin : Effect()
        data class ShowToast(val message: UiText) : Effect()
    }
}
