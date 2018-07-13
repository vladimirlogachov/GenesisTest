package com.takeiteasy.vip.genesistest.presentation.ui.ongoing

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.takeiteasy.vip.genesistest.R
import com.takeiteasy.vip.genesistest.data.network.model.Movie
import com.takeiteasy.vip.genesistest.presentation.common.GenericPagingAdapter
import com.takeiteasy.vip.genesistest.presentation.common.Shareable

class OngoingMoviesAdapter(
        val interactionsListener: OngoingMoviesInteractionsListener
) : GenericPagingAdapter<Movie>() {

    companion object {
        const val EMPTY = 0
        const val ONGOING = 1
        const val LOADING = 2
    }

    override fun provideViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ONGOING -> OngoingMovieViewHolder(inflateView(parent, R.layout.view_ongoing_item))
            EMPTY -> EmptyStateViewHolder(inflateView(parent, R.layout.view_empty))
            else -> PageLoadingViewHolder(inflateView(parent, R.layout.view_page_loading))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isEmpty() -> EMPTY
            isPageLoading() && position == getSize() -> LOADING
            else -> ONGOING
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