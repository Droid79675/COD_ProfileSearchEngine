package com.codsearchengineprofile.domain.usecases

import com.codsearchengineprofile.domain.models.ProfileDomainModel
import com.codsearchengineprofile.domain.repositories.RemoteRepository
import io.reactivex.Single
import javax.inject.Inject

class GetProfileModelUseCase @Inject constructor(val apiRepository: RemoteRepository):
    BaseUseCase<ProfileDomainModel> {
    override fun execute(): Single<ProfileDomainModel>{
        return apiRepository.getResponseProfiles()
    }
}
