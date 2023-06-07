package com.codsearchengineprofile.domain.usecases

import com.codsearchengineprofile.domain.model.ProfileDomainModel
import com.codsearchengineprofile.domain.repository.ProfileRepository
import javax.inject.Inject

class GetProfileModelUseCase @Inject constructor(private val profileRepository: ProfileRepository) {
    suspend fun getProfile(username: String, platform: String): ProfileDomainModel {
        return profileRepository.getProfileData(username, platform)
    }

    fun getKostyl(username: String, platform: String): ProfileDomainModel{
        return ProfileDomainModel(
        platform,
        username,
        353,
        0.56,
        4127,
        3768,
        1.27,
        7282,
        20636425)
    }
}
