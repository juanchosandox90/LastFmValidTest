package com.sandoval.lastfmvalidtest.presenter.base

import androidx.lifecycle.LifecycleOwner

interface BaseView : LifecycleOwner {
    fun showGenericError() = Unit
}