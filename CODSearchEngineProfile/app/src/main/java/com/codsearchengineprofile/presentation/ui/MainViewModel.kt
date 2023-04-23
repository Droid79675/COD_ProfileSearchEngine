package com.codsearchengineprofile.presentation.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.codsearchengineprofile.domain.repository.ProfileRepository
import com.codsearchengineprofile.domain.usecases.GetProfileModelUseCase
import com.codsearchengineprofile.presentation.ui.contract.Event
import com.codsearchengineprofile.presentation.ui.contract.SearchViewState
import com.codsearchengineprofile.presentation.ui.contract.SpinnerViewState
import com.codsearchengineprofile.presentation.ui.contract.State
import com.codsearchengineprofile.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseViewModel<Event, State>() {

    override fun createInitialState(): State {
        return State(
            SearchViewState.Idle,
            SpinnerViewState.Idle
        )
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnTextInputEditTextClicked -> {
                getProfiles(event.username, event.platform)
            }
            is Event.OnSpinnerClicked -> {

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
            val profileDomainModel = GetProfileModelUseCase(profileRepository).getProfile(username, platform)
            try {
                setState {
                    copy(
                        searchViewState = SearchViewState.Success(profileDomainModel)
                    )
                }
            } catch (exception: Exception) {
                Log.d("MAINVIEWMODEL", exception.toString())
            }
        }
    }
}
