package com.sandoval.lastfmvalidtest.ui.search

import com.bumptech.glide.Glide
import com.sandoval.lastfmvalidtest.R
import com.sandoval.lastfmvalidtest.common.extensions.defaultImageUrl
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Track
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.track_search_item.view.*

class TrackItem(val track: Track) : Item() {

    override fun getLayout() = R.layout.track_search_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.track.text = track.name
        viewHolder.itemView.artist.text = track.artist

        Glide.with(viewHolder.root)
            .load(track.defaultImageUrl())
            .into(viewHolder.itemView.image)
    }

}