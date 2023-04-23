package com.codsearchengineprofile.presentation.ui.contract

import com.codsearchengineprofile.domain.model.ProfileDomainModel
import com.codsearchengineprofile.presentation.interfaces.UiState

data class State (
    val searchViewState: SearchViewState,
    val spinnerViewState: SpinnerViewState
) : UiState
