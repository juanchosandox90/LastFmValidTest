package com.sandoval.lastfmvalidtest.test.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.sandoval.lastfmvalidtest.common.AppExecutors
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers

fun createTestLifecycle(event: Lifecycle.Event = Lifecycle.Event.ON_START): Lifecycle {
    return LifecycleRegistry(mockk()).also {
        it.handleLifecycleEvent(event)
    }
}

fun testAppExecutors() = AppExecutors(
    io = Dispatchers.Unconfined,
    ui = Dispatchers.Unconfined
)