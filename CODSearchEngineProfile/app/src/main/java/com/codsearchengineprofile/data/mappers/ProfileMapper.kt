package com.codsearchengineprofile.data.mappers

import com.codsearchengineprofile.data.model.ProfileDataModel
import com.codsearchengineprofile.domain.models.ProfileDomainModel
import javax.inject.Inject

class ProfileMapper @Inject constructor() {

    fun mapProfileModel(profileModelServer: ProfileDataModel): ProfileDomainModel {
        return ProfileDomainModel(
            profileModelServer.dataModel.platform,
            profileModelServer.dataModel.username,
            profileModelServer.dataModel.level,
            profileModelServer.dataModel.lifetime.all.properties.accuracy,
            profileModelServer.dataModel.lifetime.all.properties.wins,
            profileModelServer.dataModel.lifetime.all.properties.losses,
            profileModelServer.dataModel.lifetime.all.properties.kdRatio,
            profileModelServer.dataModel.lifetime.all.properties.headshots,
            profileModelServer.dataModel.lifetime.all.properties.score
        )
    }
}
