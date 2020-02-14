package com.sandoval.lastfmvalidtest.common.extensions

import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Artist

fun Artist.defaultImageUrl() = image.extraLarge.text