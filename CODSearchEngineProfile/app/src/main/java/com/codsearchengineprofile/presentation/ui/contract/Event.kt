package com.codsearchengineprofile.presentation.ui.contract

import com.codsearchengineprofile.presentation.interfaces.UiEvent

open class Event: UiEvent {
    data class OnTextInputEditTextClicked(val username: String, val platform: String) : Event()
    object OnSpinnerClicked: Event()
}
