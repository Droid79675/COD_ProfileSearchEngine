package com.codsearchengineprofile.data.di

import android.content.Context
import com.codsearchengineprofile.App
import com.codsearchengineprofile.data.di.component.MainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ProfileModule::class,
        NetworkModule::class,
        ViewModelModule::class,
    ]
)
@Singleton
interface AppComponent {

    fun provideContext(): Context

    fun plusMainComponent(): MainComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): AppComponent
    }
    fun inject(application: App)

}
