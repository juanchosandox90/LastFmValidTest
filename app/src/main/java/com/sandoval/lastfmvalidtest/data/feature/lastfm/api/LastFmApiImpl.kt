package com.sandoval.lastfmvalidtest.data.feature.lastfm.api

import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.interceptor.ApiKeyInterceptor
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.interceptor.FormatAsJsonInterceptor
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.mapper.AlbumSearchMapper
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.mapper.ArtistSearchMapper
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.mapper.TrackInfoMapper
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.mapper.TrackSearchMapper
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.service.Service
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.LastFMException
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.api.LastFmApi
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Album
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Artist
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Track
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.TrackInfo
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LastFmApiImpl(
    apiUrl: String,
    apiKeyInterceptor: ApiKeyInterceptor
) : LastFmApi {

    private val client = OkHttpClient.Builder()
        .addInterceptor(FormatAsJsonInterceptor)
        .addInterceptor(apiKeyInterceptor)
        .build()

    private val service = Retrofit.Builder()
        .baseUrl(apiUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Service::class.java)

    override fun searchTracks(query: String): List<Track> {
        return executeCall(service.searchTracks(query)) {
            TrackSearchMapper.map(it)
        }!!
    }

    override fun searchArtists(query: String): List<Artist> {
        return executeCall(service.searchArtists(query)) {
            ArtistSearchMapper.map(it)
        }!!
    }

    override fun searchAlbums(query: String): List<Album> {
        return executeCall(service.searchAlbums(query)) {
            AlbumSearchMapper.map(it)
        }!!
    }

    override fun trackById(mbid: String): TrackInfo {
        return executeCall(service.trackById(mbid)) {
            TrackInfoMapper.map(it)
        }!!
    }

    private fun <T, V> executeCall(call: Call<T>, mapper: (LastFmApiImpl.(T) -> V)?): V? {
        return call.execute().let {
            if (it.isSuccessful) {
                mapper?.invoke(this, it.body()!!)
            } else {
                val error = LastFMException.Error.HTTPError(
                    code = it.code(),
                    message = it.message()
                )

                throw LastFMException(error)
            }
        }
    }
}

