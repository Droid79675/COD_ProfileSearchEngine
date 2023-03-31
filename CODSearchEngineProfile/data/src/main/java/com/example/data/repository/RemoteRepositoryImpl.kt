package com.example.data.repository

import com.example.data.apiservice.ApiService
import com.example.data.mappers.ProfileMapper
import com.example.domain.models.ProfileDomainModel
import com.example.domain.repositories.RemoteRepository
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
