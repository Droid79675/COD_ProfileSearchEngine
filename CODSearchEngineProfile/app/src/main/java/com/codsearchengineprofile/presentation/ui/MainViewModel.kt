package com.codsearchengineprofile.presentation.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
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
            setState { copy(searchViewState = MainContract.SearchViewState.Loading) }
            try {
                val random = (0..10).random()
                if (random % 2 == 0) {
                    setState { copy(searchViewState = MainContract.SearchViewState.Idle) }
                    throw RuntimeException("Number is even")
                }
                setState { copy(searchViewState = MainContract.SearchViewState.Success(getProfileModelUseCase = random)) }
            } catch (exception: Exception) {
                Log.d("GETPROFILES", exception.toString()) }
            }
        }
}
