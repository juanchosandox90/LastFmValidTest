package com.sandoval.lastfmvalidtest.ui.search

import com.sandoval.lastfmvalidtest.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.empty_placeholder.view.*

class EmptyState(private val message: String) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.message.text = message
    }

    override fun getLayout() = R.layout.empty_placeholder
}