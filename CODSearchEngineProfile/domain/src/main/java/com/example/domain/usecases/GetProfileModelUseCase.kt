package com.example.domain.usecases

import com.example.domain.models.ProfileDomainModel
import com.example.domain.repositories.RemoteRepository
import io.reactivex.Single
import javax.inject.Inject

class GetProfileModelUseCase @Inject constructor(val apiRepository: RemoteRepository): BaseUseCase<ProfileDomainModel>{
    override fun execute(): Single<ProfileDomainModel>{
        return apiRepository.getResponseProfiles()
    }
}
