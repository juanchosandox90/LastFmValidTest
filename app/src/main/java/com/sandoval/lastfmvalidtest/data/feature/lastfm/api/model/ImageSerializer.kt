package com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model

import com.google.gson.annotations.SerializedName

data class ImageSerializer(
    @SerializedName("#text")
    val text: String,
    val size: String
)