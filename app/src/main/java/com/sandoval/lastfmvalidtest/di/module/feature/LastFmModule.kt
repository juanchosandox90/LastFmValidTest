package com.sandoval.lastfmvalidtest.di.module.feature

import com.sandoval.lastfmvalidtest.R
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val lastFmModule = module {
    single(named("api-url")) {
        androidContext().getString(R.string.lastfm_api_url)
    }
}