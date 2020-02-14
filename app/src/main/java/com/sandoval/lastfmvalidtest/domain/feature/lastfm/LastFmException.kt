package com.sandoval.lastfmvalidtest.domain.feature.lastfm

class LastFMException(
    @Suppress("MemberVisibilityCanBePrivate")
    val error: Error
) : Exception(error.message) {

    sealed class Error(val message: String) {
        class HTTPError(code: Int, message: String) : Error(
            message = "last.fm HTTP Error: code=$code, message=$message"
        )

        object TrackNotFoundError : Error("Track not found")
    }
}