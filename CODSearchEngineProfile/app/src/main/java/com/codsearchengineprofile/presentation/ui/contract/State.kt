package com.codsearchengineprofile.presentation.ui.contract

import com.codsearchengineprofile.presentation.interfaces.UiState

data class State (
    val searchViewState: SearchViewState
) : UiState
