package com.sandoval.lastfmvalidtest.di.module.feature

import com.sandoval.lastfmvalidtest.BuildConfig
import com.sandoval.lastfmvalidtest.R
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.LastFmApiImpl
import com.sandoval.lastfmvalidtest.data.feature.lastfm.LastFmRepositoryImpl
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.interceptor.ApiKeyInterceptor
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.LastFmRepository
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.api.LastFmApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val lastFmModule = module {
    single(named("api-url")) {
        androidContext().getString(R.string.lastfm_api_url)
    }
    single {
        ApiKeyInterceptor(BuildConfig.LASTFM_API_KEY)
    }

    single<LastFmApi> {
        LastFmApiImpl(
            apiUrl = get(named("api-url")),
            apiKeyInterceptor = get()
        )
    }

    // Repositories
    singleBy<LastFmRepository, LastFmRepositoryImpl>()
}