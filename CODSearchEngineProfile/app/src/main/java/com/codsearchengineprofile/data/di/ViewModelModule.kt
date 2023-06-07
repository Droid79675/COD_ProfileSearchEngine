package com.codsearchengineprofile.data.di

import androidx.lifecycle.ViewModelProvider
import com.codsearchengineprofile.utils.AppViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(
        factory: AppViewModelFactory
    ): ViewModelProvider.Factory
}
