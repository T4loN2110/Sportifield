package com.t4lon.sportifield.ui.features.home

import com.t4lon.sportifield.data.model.User
import com.t4lon.sportifield.util.UiText

class HomeContract {
    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val profile: User? = null,
        val uid: String = "",
    )

    sealed class Intent {
    }

    sealed class Effect {
        data class ShowToast(val message: UiText) : Effect()
    }
}
