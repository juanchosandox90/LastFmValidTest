package com.sandoval.lastfmvalidtest.data.feature.lastfm.api.service

import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model.AlbumSearchSerializer
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model.ArtistSearchSerializer
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model.TrackInfoSerializer
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model.TrackSearchSerializer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("/2.0/?method=album.search")
    fun searchAlbums(@Query("album") album: String): Call<AlbumSearchSerializer>

    @GET("/2.0/?method=artist.search")
    fun searchArtists(@Query("artist") artist: String): Call<ArtistSearchSerializer>

    @GET("/2.0/?method=track.search")
    fun searchTracks(@Query("track") track: String): Call<TrackSearchSerializer>

    @GET("/2.0/?method=track.getInfo")
    fun trackById(@Query("mbid") mbid: String): Call<TrackInfoSerializer>
}