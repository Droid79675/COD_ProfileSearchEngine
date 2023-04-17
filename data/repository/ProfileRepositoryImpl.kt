package com.codsearchengineprofile.data.repository

import com.codsearchengineprofile.data.apiservice.ProfileService
import com.codsearchengineprofile.data.mappers.ProfileMapper
import com.codsearchengineprofile.domain.models.ProfileDomainModel
import com.codsearchengineprofile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService,
    private val profileMapper: dagger.Lazy<ProfileMapper>) : ProfileRepository {

    override suspend fun getProfileData(username: String, platform: String): ProfileDomainModel {
        return profileMapper.get()
            .mapProfileModel(profileService.getDataProfile(username, platform))
    }
}
