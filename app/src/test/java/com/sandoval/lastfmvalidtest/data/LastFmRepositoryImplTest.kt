package com.sandoval.lastfmvalidtest.data

import com.sandoval.lastfmvalidtest.data.feature.lastfm.LastFmRepositoryImpl
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.api.LastFmApi
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.*
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LastFmRepositoryImplTest {

    @MockK
    private lateinit var api: LastFmApi

    @InjectMockKs
    private lateinit var repository: LastFmRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `on music search, retrieve data`() = runBlocking {
        val tracks = listOf(mockk<Track>())
        val artists = listOf(mockk<Artist>())
        val albums = listOf(mockk<Album>())

        every {
            api.searchArtists(any())
        } returns artists

        every {
            api.searchTracks(any())
        } returns tracks

        every {
            api.searchAlbums(any())
        } returns albums

        val actual = repository.searchMusic("query")

        val expected = MusicSearch(
            tracks = tracks,
            artists = artists,
            albums = albums
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `track by id`() = runBlocking {
        val track = mockk<TrackInfo>()

        every {
            api.trackById("1234")
        } returns track

        Assert.assertEquals(repository.trackById("1234"), track)
    }
}