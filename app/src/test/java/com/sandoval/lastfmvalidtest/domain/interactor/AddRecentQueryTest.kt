package com.sandoval.lastfmvalidtest.domain.interactor

import com.sandoval.lastfmvalidtest.domain.feature.common.preferences.AppPreferences
import com.sandoval.lastfmvalidtest.domain.feature.common.preferences.interactor.AddRecentQuery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AddRecentQueryTest {

    private lateinit var addRecentQuery: AddRecentQuery
    private lateinit var appPreferences: AppPreferences

    @Before
    fun setUp() {
        appPreferences = mockk(relaxUnitFun = true)
        addRecentQuery = AddRecentQuery(appPreferences)
    }

    @Test
    fun `add new query`() {
        every {
            appPreferences.recentQueries
        } returns emptyList()

        addRecentQuery.execute("1234")

        verify {
            appPreferences.recentQueries = withArg {
                Assert.assertTrue(it.first().startsWith("1234"))
            }
        }
    }

    @Test
    fun `update query date on matching query`() {
        every {
            appPreferences.recentQueries
        } returns listOf("1234:0")

        addRecentQuery.execute("1234")

        verify {
            appPreferences.recentQueries = withArg {
                Assert.assertTrue(it.size == 1)
                Assert.assertTrue(it.first().startsWith("1234"))
            }
        }
    }

    @Test
    fun `limit update to 100 queries`() {
        every {
            appPreferences.recentQueries
        } returns 0.until(2000).map { "$it:0" }

        addRecentQuery.execute("1234")

        verify {
            appPreferences.recentQueries = withArg {
                Assert.assertTrue(it.size == 100)
            }
        }
    }

}