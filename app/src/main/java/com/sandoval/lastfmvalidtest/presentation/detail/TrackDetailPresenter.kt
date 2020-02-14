package com.sandoval.lastfmvalidtest.presentation.detail

import com.sandoval.lastfmvalidtest.common.AppExecutors
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.LastFMException
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.LastFmRepository
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.TrackInfo
import com.sandoval.lastfmvalidtest.presenter.base.BasePresenter
import com.sandoval.lastfmvalidtest.presenter.base.BaseView
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class TrackDetailPresenter(
    private val appExecutors: AppExecutors,
    private val lastFmRepository: LastFmRepository
) : BasePresenter<TrackDetailPresenter.View>(appExecutors) {

    lateinit var trackId: String

    override fun bind(view: View) {
        super.bind(view)
        loadTrack()
    }

    private fun loadTrack() {
        uiScope.launch {
            try {
                val track = withContext(appExecutors.io) {
                    lastFmRepository.trackById(trackId)
                }
                view?.showTrackInfo(track)
            } catch (e: Exception) {
                when ((e as? LastFMException)?.error) {
                    LastFMException.Error.TrackNotFoundError -> {
                        Timber.e(e, "Unable to find track ($trackId)")
                        view?.showGenericError()
                    }
                    else -> {
                        Timber.e(e, "Error loading track")
                        view?.showGenericError()
                    }
                }
            }
        }
    }

    interface View : BaseView {
        fun showTrackInfo(trackInfo: TrackInfo)
    }
}