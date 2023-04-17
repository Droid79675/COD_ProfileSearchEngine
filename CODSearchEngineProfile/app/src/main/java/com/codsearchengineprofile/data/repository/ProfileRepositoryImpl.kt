package com.codsearchengineprofile.data.repository

import com.codsearchengineprofile.data.apiservice.ProfileService
import com.codsearchengineprofile.data.mappers.ProfileMapper
import com.codsearchengineprofile.domain.models.ProfileDomainModel
import com.codsearchengineprofile.domain.repositories.ProfileDomainRepository
import io.reactivex.Single
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService,
    private val profileMapper: dagger.Lazy<ProfileMapper>) : ProfileDomainRepository {

    override fun getDomainProfile(): Single<ProfileDomainModel> {
        return profileService.getDataProfile()
            .map {
                profileMapper.get().mapProfileModel(it)
            }
    }
}
