package com.sandoval.lastfmvalidtest.common.extensions

import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Track

fun Track.defaultImageUrl() = image.extraLarge.text