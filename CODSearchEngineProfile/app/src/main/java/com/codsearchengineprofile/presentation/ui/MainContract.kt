package com.codsearchengineprofile.presentation.ui

import com.codsearchengineprofile.domain.usecases.GetProfileModelUseCase
import com.codsearchengineprofile.presentation.interfaces.UiEffect
import com.codsearchengineprofile.presentation.interfaces.UiEvent
import com.codsearchengineprofile.presentation.interfaces.UiState

class MainContract {

    sealed class Event : UiEvent {
        object OnTextInputEditTextClicked : Event()
        object OnSpinnerClicked: Event()
    }

    data class State(
        val searchViewState: SearchViewState,
        val spinnerViewState: SpinnerViewState
    ) : UiState

    sealed class SearchViewState {
        object Idle : SearchViewState()
        object Loading : SearchViewState()
        data class Success(val getProfileModelUseCase : GetProfileModelUseCase) : SearchViewState()
    }

    sealed class SpinnerViewState {
        object Idle : SpinnerViewState()
        object Changed : SpinnerViewState()
    }
}
