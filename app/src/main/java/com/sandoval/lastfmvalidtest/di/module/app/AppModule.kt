package com.sandoval.lastfmvalidtest.di.module.app
import com.sandoval.lastfmvalidtest.domain.feature.common.preferences.interactor.AddRecentQuery
import com.sandoval.lastfmvalidtest.domain.feature.common.preferences.interactor.RecentQueries
import org.koin.dsl.module
import org.koin.experimental.builder.single

val appModule = module {
    // Interactors
    single<AddRecentQuery>()
    single<RecentQueries>()
}

