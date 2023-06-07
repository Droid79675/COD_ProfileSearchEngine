package com.codsearchengineprofile.data.di.component

import androidx.fragment.app.FragmentActivity
import com.codsearchengineprofile.presentation.MainActivity
import com.codsearchengineprofile.presentation.ui.MainFragment
import com.codsearchengineprofile.utils.AppViewModelFactory
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun inject(fragment: MainFragment)

    fun appViewModelFactory(): AppViewModelFactory

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainComponent
    }
}
