package com.sandoval.lastfmvalidtest.common.extensions

import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.TrackInfo

fun TrackInfo.defaultImageUrl() = album.image.extraLarge.text