package com.sandoval.lastfmvalidtest.view.base

import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Album
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Artist
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.MusicSearch
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Track
import com.sandoval.lastfmvalidtest.presenter.base.BaseView

interface SearchView : BaseView {
    fun showSearchResult(searchResult: MusicSearch)
    fun clearSearchResult()
    fun clearSearchText()
    fun showLoading()
    fun hideLoading()
    fun showEmptyPlaceholder()
    fun showNoResultsPlaceholder()
    fun hidePlaceholder()
    fun setRecentQueries(queries: List<String>)
    fun showTrackDetail(track: Track)
    fun showAlbumDetail(album: Album)
    fun showArtistDetail(artist: Artist)
}