package com.sandoval.lastfmvalidtest.ui.search

import com.sandoval.lastfmvalidtest.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_header_item.view.*

class HeaderItem(private val title: String) : Item() {

    override fun getLayout() = R.layout.search_header_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.title.text = title
    }
}