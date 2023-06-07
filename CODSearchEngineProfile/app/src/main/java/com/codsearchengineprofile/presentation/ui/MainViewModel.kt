package com.codsearchengineprofile.presentation.ui

import androidx.lifecycle.viewModelScope
import com.codsearchengineprofile.domain.usecases.GetProfileModelUseCase
import com.codsearchengineprofile.presentation.ui.contract.Event
import com.codsearchengineprofile.presentation.ui.contract.SearchViewState
import com.codsearchengineprofile.presentation.ui.contract.State
import com.codsearchengineprofile.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getProfileModelUseCase: GetProfileModelUseCase
) : BaseViewModel<Event, State>() {

    override fun createInitialState(): State {
        return State(
            SearchViewState.Idle
        )
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnTextInputEditTextClicked -> {
                getProfiles(event.username, event.platform)
            }
            is Event.ErrorNoInternet -> {
                throwError()
            }
        }
    }

    private fun throwError(){
        viewModelScope.launch {
            setState {
                copy(
                    searchViewState = SearchViewState.Error
                )
            }
        }
    }

    private fun getProfiles(username: String, platform: String) {
        viewModelScope.launch {
            setState {
                copy(
                    searchViewState = SearchViewState.Loading(username, platform)
                )
            }
            val profileDomainModel = getProfileModelUseCase.getKostyl(username, platform)
            setState {
                copy(
                    searchViewState = SearchViewState.Success(profileDomainModel)
                )
            }
        }
    }
}
