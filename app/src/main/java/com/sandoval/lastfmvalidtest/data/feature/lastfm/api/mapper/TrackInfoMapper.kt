package com.sandoval.lastfmvalidtest.data.feature.lastfm.api.mapper

import com.sandoval.lastfmvalidtest.common.Mapper
import com.sandoval.lastfmvalidtest.data.feature.lastfm.api.model.TrackInfoSerializer
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.LastFMException
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.TrackInfo

object TrackInfoMapper : Mapper<TrackInfoSerializer, TrackInfo>() {
    override fun map(input: TrackInfoSerializer): TrackInfo {
        return mapTrack(input.track ?: throw LastFMException(LastFMException.Error.TrackNotFoundError))
    }

    private fun mapTrack(input: TrackInfoSerializer.Track): TrackInfo {
        return TrackInfo(
            mbid = input.mbid,
            artist = input.artist.let { artist ->
                TrackInfo.Artist(
                    name = artist.name,
                    mbid = artist.mbid
                )
            },
            album = input.album.let { album ->
                TrackInfo.Album(
                    mbid = album.mbid,
                    artist = album.artist,
                    image = ImageCollectionMapper.map(album.image),
                    title = album.title
                )
            },
            url = input.url,
            name = input.name,
            wiki = input.wiki?.let { wiki ->
                TrackInfo.Wiki(
                    published = wiki.published,
                    content = wiki.content,
                    summary = wiki.summary
                )
            }
        )
    }
}