package com.codsearchengineprofile.presentation.ui.contract

import javax.inject.Inject

open class SpinnerViewState {
    object Idle : SpinnerViewState()
    data class Opened @Inject constructor(val platforms: MutableList<String>, val imagesIds: MutableList<Int>) : SpinnerViewState()
    data class ChangedImage @Inject constructor(val platform: String) : SpinnerViewState()
}
