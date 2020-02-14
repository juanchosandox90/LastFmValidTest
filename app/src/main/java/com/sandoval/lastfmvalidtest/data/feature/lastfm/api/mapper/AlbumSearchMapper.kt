package com.sandoval.lastfmvalidtest.data.feature.lastfm.api.mapper

import com.sandoval.lastfmvalidtest.common.Mapper
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model.AlbumSearchSerializer
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Album

object AlbumSearchMapper : Mapper<AlbumSearchSerializer, List<Album>>() {
    override fun map(input: AlbumSearchSerializer): List<Album> {
        return input.results.albummatches.album.map(AlbumMapper::map)
    }
}