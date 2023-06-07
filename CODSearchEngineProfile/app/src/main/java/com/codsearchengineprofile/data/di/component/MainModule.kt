package com.codsearchengineprofile.data.di.component

import androidx.lifecycle.ViewModel
import com.codsearchengineprofile.data.di.ViewModelKey
import com.codsearchengineprofile.presentation.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun provideViewModel(viewModel: MainViewModel): ViewModel
}
