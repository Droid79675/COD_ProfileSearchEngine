package com.codsearchengineprofile

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.codsearchengineprofile.data.di.AppComponent
import com.codsearchengineprofile.data.di.DaggerAppComponent
import timber.log.Timber

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
            .context(applicationContext)
            .build().apply {
                inject(this@App)
            }
    }

    companion object {
        lateinit var appComponent: AppComponent
    }

}
