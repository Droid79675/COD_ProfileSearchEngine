package com.codsearchengineprofile.presentation.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.codsearchengineprofile.domain.usecases.GetProfileModelUseCase
import com.codsearchengineprofile.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel<MainContract.Event, MainContract.State>() {

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            MainContract.SearchViewState.Idle,
            MainContract.SpinnerViewState.Idle
        )
    }

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnTextInputEditTextClicked -> {
                getProfiles()
            }
            is MainContract.Event.OnSpinnerClicked -> {

            }
        }
    }

    private fun getProfiles() {
        viewModelScope.launch {
            setState {
                copy(
                    searchViewState = MainContract.SearchViewState.Loading(
                        getProfileModelUseCase = GetProfileModelUseCase()
                    )
                )
            }
            try {
                setState {
                    copy(
                        searchViewState = MainContract.SearchViewState.Success
                    )
                }
            } catch (exception: Exception) {
                Log.d("MAINVIEWMODEL", exception.toString())
            }
        }
    }
}
