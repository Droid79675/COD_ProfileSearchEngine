package com.codsearchengineprofile.presentation.ui

import androidx.lifecycle.viewModelScope
import com.codsearchengineprofile.R
import com.codsearchengineprofile.domain.usecases.GetProfileModelUseCase
import com.codsearchengineprofile.presentation.ui.contract.Event
import com.codsearchengineprofile.presentation.ui.contract.SearchViewState
import com.codsearchengineprofile.presentation.ui.contract.SpinnerViewState
import com.codsearchengineprofile.presentation.ui.contract.State
import com.codsearchengineprofile.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getProfileModelUseCase: GetProfileModelUseCase
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
                showPlatforms()
            }
            is Event.OnSpinnerChose -> {
                givePlatform(event.platform)
            }
        }
    }

    private fun showPlatforms(){
        val platforms =
            mutableListOf(
                "PSN",
                "XBN",
                "BTL"
            )
        val imagesIds: MutableList<Int> = mutableListOf(
            R.drawable.icon_playstation,
            R.drawable.icon_xbox,
            R.drawable.icon_battlenet
        )
        viewModelScope.launch {
            setState {
                copy(
                    spinnerViewState = SpinnerViewState.Opened(platforms, imagesIds)
                )
            }
        }
    }

    private fun givePlatform(platform: String) {
        viewModelScope.launch {
            setState {
                copy(
                    spinnerViewState = SpinnerViewState.ChangedImage(platform)
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
            val profileDomainModel = getProfileModelUseCase.getProfile(username, platform)
            setState {
                copy(
                    searchViewState = SearchViewState.Success(profileDomainModel)
                )
            }
        }
    }
}
