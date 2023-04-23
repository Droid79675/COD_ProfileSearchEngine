package com.codsearchengineprofile.presentation.ui.contract

import com.codsearchengineprofile.domain.model.ProfileDomainModel
import javax.inject.Inject

open class SearchViewState {
    object Idle : SearchViewState()
    data class Loading @Inject constructor(val username:String, val platform: String) : SearchViewState()
    data class Success @Inject constructor(val profileDomainModel: ProfileDomainModel): SearchViewState()
}
