package com.sandoval.lastfmvalidtest.common.extensions

import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.MusicSearch

fun MusicSearch.isEmpty() = tracks.isEmpty() && artists.isEmpty() && albums.isEmpty()