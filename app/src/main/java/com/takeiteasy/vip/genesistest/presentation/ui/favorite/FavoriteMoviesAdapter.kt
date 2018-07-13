package com.takeiteasy.vip.genesistest.presentation.ui.favorite

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.takeiteasy.vip.genesistest.R
import com.takeiteasy.vip.genesistest.data.network.model.Movie
import com.takeiteasy.vip.genesistest.presentation.common.GenericPagingAdapter
import com.takeiteasy.vip.genesistest.presentation.common.Shareable

class FavoriteMoviesAdapter(
        val interactionsListener: FavoriteMoviesInteractionsListener
) : GenericPagingAdapter<Movie>() {
    companion object {
        const val EMPTY = 0
        const val FAVORITE = 1
    }

    override fun provideViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FAVORITE -> OngoingMovieViewHolder(inflateView(parent, R.layout.view_favorite_item))
            else -> EmptyStateViewHolder(inflateView(parent, R.layout.view_empty))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isEmpty() -> EMPTY
            else -> FAVORITE
        }
    }

    inner class OngoingMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), Binder<Movie> {
        override fun bind(item: Movie) = with(itemView) {
            Glide.with(itemView).load(item.getPosterUrl()).into(poster)
            title.text = item.title
            description.text = item.overview
            remove.setOnClickListener {
                interactionsListener.removeMovieToFavorite(item)
                removeItem(adapterPosition)
            }
            share.setOnClickListener{
                interactionsListener.share(item.asStringForShare())
            }
        }
    }

    interface OngoingMoviesInteractionsListener : Shareable {
        fun addMovieToFavorite(movie: Movie)
    }

    interface FavoriteMoviesInteractionsListener : Shareable {
        fun removeMovieToFavorite(movie: Movie)
    }
}