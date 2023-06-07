package com.codsearchengineprofile.presentation.ui.contract

import com.codsearchengineprofile.presentation.interfaces.UiEvent
import javax.inject.Inject

open class Event : UiEvent {
    data class OnTextInputEditTextClicked @Inject constructor(
        val username: String,
        val platform: String
    ) : Event()
    object ErrorNoInternet : Event()
}
