package com.codsearchengineprofile.data.repository

import com.codsearchengineprofile.data.apiservice.ApiService
import com.codsearchengineprofile.data.mappers.ProfileMapper
import com.codsearchengineprofile.domain.models.ProfileDomainModel
import com.codsearchengineprofile.domain.repositories.RemoteRepository
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val profileMapper: dagger.Lazy<ProfileMapper>) : RemoteRepository {

    override fun getResponseProfiles(): Single<ProfileDomainModel> {
        return apiService.getProfileDomains()
            .map {
                profileMapper.get().mapProfileModel(it)
            }
    }
}
