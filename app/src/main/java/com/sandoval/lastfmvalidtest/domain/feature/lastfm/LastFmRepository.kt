package com.sandoval.lastfmvalidtest.domain.feature.lastfm

import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.MusicSearch
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.TrackInfo

interface LastFmRepository {
    suspend fun searchMusic(query: String): MusicSearch
    suspend fun trackById(mbid: String): TrackInfo
}