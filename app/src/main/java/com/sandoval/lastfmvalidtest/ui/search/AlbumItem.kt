package com.sandoval.lastfmvalidtest.ui.search

import com.bumptech.glide.Glide
import com.sandoval.lastfmvalidtest.R
import com.sandoval.lastfmvalidtest.common.extensions.defaultImageUrl
import com.sandoval.lastfmvalidtest.domain.feature.lastfm.model.Album
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.album_search_item.view.*

class AlbumItem(val album: Album) : Item() {

    override fun getLayout() = R.layout.album_search_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.album.text = album.name
        viewHolder.itemView.artist.text = album.artist
        Glide.with(viewHolder.root)
            .load(album.defaultImageUrl())
            .into(viewHolder.itemView.image)
    }
}