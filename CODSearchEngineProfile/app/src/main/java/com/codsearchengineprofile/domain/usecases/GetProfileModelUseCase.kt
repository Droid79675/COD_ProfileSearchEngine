package com.codsearchengineprofile.domain.usecases

import com.codsearchengineprofile.domain.models.ProfileDomainModel
import com.codsearchengineprofile.domain.repositories.ProfileDomainRepository
import io.reactivex.Single
import javax.inject.Inject

class GetProfileModelUseCase @Inject constructor(val apiRepository: ProfileDomainRepository):
    BaseUseCase<ProfileDomainModel> {
    override fun execute(): Single<ProfileDomainModel>{
        return apiRepository.getDomainProfile()
    }
}
