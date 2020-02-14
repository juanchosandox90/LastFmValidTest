package com.sandoval.lastfmvalidtest.domain.interactor

import com.sandoval.lastfmvalidtest.domain.feature.common.preferences.AppPreferences
import com.sandoval.lastfmvalidtest.domain.feature.common.preferences.interactor.RecentQueries
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class RecentQueriesTest {

    private lateinit var appPreferences: AppPreferences
    private lateinit var recentQueries: RecentQueries

    @Before
    fun setUp() {
        appPreferences = mockk(relaxUnitFun = true)
        recentQueries = RecentQueries(appPreferences)
    }

    @Test
    fun `execute returns list of queries`() {
        every { appPreferences.recentQueries } returns listOf("1234:0")

        TestCase.assertEquals(recentQueries.execute(), listOf("1234"))
    }

}