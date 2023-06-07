package com.codsearchengineprofile.data.di

import com.codsearchengineprofile.data.apiservice.ProfileService
import com.codsearchengineprofile.data.mappers.ProfileMapper
import com.codsearchengineprofile.data.repository.ProfileRepositoryImpl
import com.codsearchengineprofile.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {
    @Provides
    fun provideWeatherRepository(
        profileService: ProfileService,
        profileMapper: dagger.Lazy<ProfileMapper>
    ): ProfileRepository = ProfileRepositoryImpl(profileService, profileMapper)
}
