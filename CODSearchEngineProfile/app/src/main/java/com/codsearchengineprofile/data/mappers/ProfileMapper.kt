package com.codsearchengineprofile.data.mappers

import com.codsearchengineprofile.data.model.ProfileDataModel
import com.codsearchengineprofile.domain.model.ProfileDomainModel
import dagger.Lazy
import javax.inject.Inject

class ProfileMapper @Inject constructor() {

    fun mapProfileModel(profileDataModel: ProfileDataModel): ProfileDomainModel {
        return ProfileDomainModel(
            profileDataModel.dataModel.platform,
            profileDataModel.dataModel.username,
            profileDataModel.dataModel.level,
            profileDataModel.dataModel.lifetime.all.properties.accuracy,
            profileDataModel.dataModel.lifetime.all.properties.wins,
            profileDataModel.dataModel.lifetime.all.properties.losses,
            profileDataModel.dataModel.lifetime.all.properties.kdRatio,
            profileDataModel.dataModel.lifetime.all.properties.headshots,
            profileDataModel.dataModel.lifetime.all.properties.score
        )
    }
}
