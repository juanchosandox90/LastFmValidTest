package com.sandoval.lastfmvalidtest.data.feature.lastfm.api.mapper

import com.sandoval.lastfmvalidtest.common.Mapper
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model.ArtistSearchSerializer
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Artist

object ArtistSearchMapper : Mapper<ArtistSearchSerializer, List<Artist>>() {
    override fun map(input: ArtistSearchSerializer): List<Artist> {
        return input.results.artistmatches.artist.map(ArtistMapper::map)
    }
}