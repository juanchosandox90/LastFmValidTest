package com.sandoval.lastfmvalidtest.data.feature.lastfm.api

import com.sandoval.lastfmvalidtest.domain.feature.lastfm.LastFmRepository
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.api.LastFmApi
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.MusicSearch
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.TrackInfo
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class LastFmRepositoryImpl(
    private val lastFmApi: LastFmApi
) : LastFmRepository {

    override suspend fun searchMusic(query: String): MusicSearch {
        return runBlocking {
            val tracks = async { lastFmApi.searchTracks(query) }
            val artists = async { lastFmApi.searchArtists(query) }
            val albums = async { lastFmApi.searchAlbums(query) }

            MusicSearch(
                artists = artists.await(),
                tracks = tracks.await(),
                albums = albums.await()
            )
        }
    }

    override suspend fun trackById(mbid: String): TrackInfo {
        return lastFmApi.trackById(mbid)
    }
}