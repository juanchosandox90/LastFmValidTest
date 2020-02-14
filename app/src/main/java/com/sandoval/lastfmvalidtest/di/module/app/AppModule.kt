package com.sandoval.lastfmvalidtest.di.module.app

import com.sandoval.lastfmvalidtest.common.AppExecutors
import com.sandoval.lastfmvalidtest.data.feature.common.preferences.AppPreferencesImpl
import com.sandoval.lastfmvalidtest.domain.feature.common.preferences.AppPreferences
import com.sandoval.lastfmvalidtest.domain.feature.common.preferences.interactor.AddRecentQuery
import com.sandoval.lastfmvalidtest.domain.feature.common.preferences.interactor.RecentQueries
import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

val appModule = module {

    single { AppExecutors() }

    // Services
    singleBy<AppPreferences, AppPreferencesImpl>()

    // Interactors
    single<AddRecentQuery>()
    single<RecentQueries>()
}

