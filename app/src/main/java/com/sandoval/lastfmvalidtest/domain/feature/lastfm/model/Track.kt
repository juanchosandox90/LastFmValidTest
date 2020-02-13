package com.sandoval.lastfmvalidtest.domain.feature.lastfm.model

data class Track(
    val mbid: String,
    val name: String,
    val artist: String,
    val url: String,
    val image: ImageCollection
)