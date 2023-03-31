package com.example.data.mappers

import com.example.data.model.ProfileDataModel
import com.example.domain.models.ProfileDomainModel
import javax.inject.Inject

class ProfileMapper @Inject constructor() {

    fun mapProfileModel(profileModelServer: ProfileDataModel): ProfileDomainModel {
        return ProfileDomainModel(
            profileModelServer.statusCode,
            profileModelServer.dataModel,
        )
    }
}
