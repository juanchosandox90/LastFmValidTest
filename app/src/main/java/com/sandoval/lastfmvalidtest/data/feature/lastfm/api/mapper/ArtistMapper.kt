package com.sandoval.lastfmvalidtest.data.feature.lastfm.api.mapper

import com.sandoval.lastfmvalidtest.common.Mapper
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model.ArtistSerializer
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Artist

object ArtistMapper : Mapper<ArtistSerializer, Artist>() {
    override fun map(input: ArtistSerializer): Artist {
        return Artist(
            mbid = input.mbid,
            image = ImageCollectionMapper.map(input.image),
            name = input.name,
            listeners = input.listeners.toIntOrNull() ?: 0,
            url = input.url
        )
    }
}