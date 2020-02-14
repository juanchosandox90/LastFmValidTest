package com.sandoval.lastfmvalidtest.data.feature.lastfm.api.mapper

import com.sandoval.lastfmvalidtest.common.Mapper
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model.TrackSerializer
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Track

object TrackMapper : Mapper<TrackSerializer, Track>() {
    override fun map(input: TrackSerializer): Track {
        return Track(
            mbid = input.mbid,
            image = ImageCollectionMapper.map(input.image),
            name = input.name,
            artist = input.artist,
            url = input.url
        )
    }
}