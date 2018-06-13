package com.takeiteasy.vip.genesistest.presentation.ui.ongoing

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.takeiteasy.vip.genesistest.R
import com.takeiteasy.vip.genesistest.domain.model.Movie
import com.takeiteasy.vip.genesistest.presentation.common.GenericAdapter
import com.takeiteasy.vip.genesistest.presentation.common.Shareable
import kotlinx.android.synthetic.main.view_ongoing_item.view.*

class OngoingMoviesAdapter(
        val interactionsListener: OngoingMoviesInteractionsListener
) : GenericAdapter<Movie>() {

    companion object {
        const val EMPTY = 0
        const val ONGOING = 1
    }

    override fun provideViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ONGOING -> OngoingMovieViewHolder(inflateView(parent, R.layout.view_ongoing_item))
            else -> EmptyStateViewHolder(inflateView(parent, R.layout.view_empty))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isEmpty()) {
            EMPTY
        } else {
            ONGOING
        }
    }

    inner class OngoingMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), Binder<Movie> {
        override fun bind(item: Movie) = with(itemView) {
            Glide.with(itemView).load(item.getPosterUrl()).into(poster)
            title.text = item.title
            description.text = item.overview
            addToFavorite.setOnClickListener { interactionsListener.addMovieToFavorite(item) }
            share.setOnClickListener{ interactionsListener.share(item.asStringForShare()) }
        }
    }

    interface OngoingMoviesInteractionsListener : Shareable {
        fun addMovieToFavorite(movie: Movie)
    }
}