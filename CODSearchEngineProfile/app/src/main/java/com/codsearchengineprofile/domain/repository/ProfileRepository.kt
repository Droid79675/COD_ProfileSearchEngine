package com.codsearchengineprofile.domain.repository

import com.codsearchengineprofile.domain.model.ProfileDomainModel

interface ProfileRepository {
    suspend fun getProfileData(username: String, platform: String): ProfileDomainModel
}
