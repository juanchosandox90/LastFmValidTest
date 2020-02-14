package com.sandoval.lastfmvalidtest.data.feature.lastfm.api.mapper

import com.sandoval.lastfmvalidtest.common.Mapper
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model.AlbumSerializer
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Album

object AlbumMapper : Mapper<AlbumSerializer, Album>() {
    override fun map(input: AlbumSerializer): Album {
        return Album(
            mbid = input.mbid,
            image = ImageCollectionMapper.map(input.image),
            name = input.name,
            artist = input.artist,
            url = input.url
        )
    }
}