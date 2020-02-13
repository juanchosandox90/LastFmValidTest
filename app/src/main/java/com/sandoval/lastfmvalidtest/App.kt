package com.sandoval.lastfmvalidtest

import android.app.Application
import com.sandoval.lastfmvalidtest.di.module.app.appModule
import com.sandoval.lastfmvalidtest.di.module.feature.lastFmModule
import com.sandoval.lastfmvalidtest.di.module.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)

            modules(
                appModule,
                lastFmModule,
                uiModule
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}