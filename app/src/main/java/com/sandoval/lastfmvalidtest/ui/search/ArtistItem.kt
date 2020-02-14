package com.sandoval.lastfmvalidtest.ui.search

import com.bumptech.glide.Glide
import com.sandoval.lastfmvalidtest.R
import com.sandoval.lastfmvalidtest.common.extensions.defaultImageUrl
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Artist
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.artist_search_item.view.*

class ArtistItem(val artist: Artist) : Item() {

    override fun getLayout() = R.layout.artist_search_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val context = viewHolder.root.context

        viewHolder.itemView.listeners.text = context.getString(R.string.artist_listeners, artist.listeners)
        viewHolder.itemView.artist.text = artist.name

        Glide.with(viewHolder.root)
            .load(artist.defaultImageUrl())
            .into(viewHolder.itemView.image)
    }

}