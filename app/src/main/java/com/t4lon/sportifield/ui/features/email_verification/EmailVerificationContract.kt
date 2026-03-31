package com.t4lon.sportifield.ui.features.email_verification

import com.t4lon.sportifield.util.UiText

class EmailVerificationContract {
    data class State(
        val error: UiText? = null,
        val successMessage: UiText? = null,
        val isEmailVerified: Boolean = false,
        val isPolling: Boolean = false
    )

    sealed class Intent {
        data object StartEmailVerification : Intent()
        data object ResendVerificationEmail : Intent()
    }

    sealed class Effect {
        data object NavigateToHome: Effect()
        data class ShowToast(val message: UiText) : Effect()
    }
}

