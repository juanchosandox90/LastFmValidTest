package com.sandoval.lastfmvalidtest.di.module.ui


import com.sandoval.lastfmvalidtest.presentation.search.SearchPresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { SearchPresenter(get(), get(), get(), get()) }
}