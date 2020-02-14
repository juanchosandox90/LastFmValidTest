package com.sandoval.lastfmvalidtest.data.feature.lastfm.api.mapper

import com.sandoval.lastfmvalidtest.common.Mapper
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model.TrackSearchSerializer
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Track

object TrackSearchMapper : Mapper<TrackSearchSerializer, List<Track>>() {
    override fun map(input: TrackSearchSerializer): List<Track> {
        return input.results.trackmatches.track.map(TrackMapper::map)
    }
}