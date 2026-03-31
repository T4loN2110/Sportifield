package com.t4lon.sportifield.ui.features.email_verification

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
import kotlinx.coroutines.delay

import com.t4lon.sportifield.R
import com.t4lon.sportifield.util.UiText

class EmailVerificationViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _state = MutableStateFlow(EmailVerificationContract.State())
    val state: StateFlow<EmailVerificationContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<EmailVerificationContract.Effect>()
    val effect = _effect.asSharedFlow()

    private val emailVerificationSent = UiText.StringResource(R.string.email_verification_msg_sent)
    private val emailVerificationFailed = UiText.StringResource(R.string.email_verification_msg_failed)

    fun handleIntent(intent: EmailVerificationContract.Intent) {
        when (intent) {
            is EmailVerificationContract.Intent.StartEmailVerification -> startEmailVerification()
            is EmailVerificationContract.Intent.ResendVerificationEmail -> resendVerificationEmail()
        }
    }

    private fun startEmailVerification() {
        viewModelScope.launch {
            _state.update { it.copy(isPolling = true, error = null) }

            while (state.value.isPolling) {
                val user = auth.currentUser
                user?.reload()?.await()

                if (user?.isEmailVerified == true) {
                    _effect.emit(EmailVerificationContract.Effect.NavigateToHome)
                    _state.update { it.copy(isPolling = false, isEmailVerified = true) }
                    break
                } 

                delay(3000)
            }
        }
    }

    private fun resendVerificationEmail() {
        viewModelScope.launch {
            val user = auth.currentUser
            if (user != null) {
                try {
                    user.sendEmailVerification().await()
                    _effect.emit(EmailVerificationContract.Effect.ShowToast(emailVerificationSent))
                } catch (e: Exception) {
                    _effect.emit(EmailVerificationContract.Effect.ShowToast(emailVerificationFailed))
                }
            }

            startEmailVerification()
        }
    }
}