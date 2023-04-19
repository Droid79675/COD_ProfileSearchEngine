package com.codsearchengineprofile.domain.repositories

import com.codsearchengineprofile.domain.models.ProfileDomainModel
import io.reactivex.Single

interface RemoteRepository {
    fun getResponseProfiles(): Single<ProfileDomainModel>
}
