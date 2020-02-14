package com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model

data class ArtistSearchSerializer(val results: Results) {
    data class Results(val artistmatches: ArtistMatches) {
        data class ArtistMatches(val artist: List<ArtistSerializer>)
    }
}