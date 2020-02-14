package com.sandoval.lastfmvalidtest.presentation.search

import com.sandoval.lastfmvalidtest.common.AppExecutors
import com.sandoval.lastfmvalidtest.common.extensions.isEmpty
import com.sandoval.lastfmvalidtest.domain.feature.common.preferences.interactor.AddRecentQuery
import com.sandoval.lastfmvalidtest.domain.feature.common.preferences.interactor.RecentQueries
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.LastFmRepository
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Album
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Artist
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.MusicSearch
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Track
import com.sandoval.lastfmvalidtest.presenter.base.BasePresenter
import com.sandoval.lastfmvalidtest.presenter.base.BaseView
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SearchPresenter(
    private val executors: AppExecutors,
    private val repository: LastFmRepository,
    private val getRecentQueries: RecentQueries,
    private val addRecentQuery: AddRecentQuery
) : BasePresenter<SearchPresenter.View>(executors) {

    private var queryJob: Job? = null
    private var result: MusicSearch? = null

    override fun bind(view: View) {
        super.bind(view)
        result?.let(this::handleResult) ?: run {
            view.showEmptyPlaceholder()
            loadRecentQueries()
        }
    }

    fun query(query: String) {
        Timber.d("querying: $query")

        if (queryJob?.isActive == true) {
            Timber.d("Query Job is already active")
            return
        }

        queryJob = uiScope.launch {
            if (query.isEmpty()) {
                return@launch
            }

            view?.clearSearchText()
            view?.clearSearchResult()
            view?.hidePlaceholder()
            view?.showLoading()

            try {
                result = withContext(executors.io) {
                    repository.searchMusic(query)
                }.also {
                    handleResult(it)
                    if (!it.isEmpty()) {
                        addRecentQuery(query)
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Error searching for music")

                view?.showGenericError()
                view?.hideLoading()
            }
        }
    }

    private fun addRecentQuery(query: String) {
        ioScope.launch {
            try {
                addRecentQuery.execute(query)
                loadRecentQueries()
            } catch (e: Exception) {
                Timber.e(e, "Failed to add recent query")
            }
        }
    }

    private fun handleResult(result: MusicSearch) {
        view?.hideLoading()

        if (result.isEmpty()) {
            view?.showNoResultsPlaceholder()
        } else {
            view?.hidePlaceholder()
            view?.showSearchResult(result)
        }
    }

    private fun loadRecentQueries() {
        uiScope.launch {
            try {
                val queries = withContext(executors.io) {
                    getRecentQueries.execute()
                }
                view?.setRecentQueries(queries)
            } catch (e: Exception) {
                Timber.e(e, "Failed to retrieve queries")
                view?.showGenericError()
            }
        }
    }

    interface View : BaseView {
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
}