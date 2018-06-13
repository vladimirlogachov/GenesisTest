package com.takeiteasy.vip.genesistest.presentation.ui.ongoing

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.takeiteasy.vip.genesistest.R
import com.takeiteasy.vip.genesistest.domain.model.Movie
import com.takeiteasy.vip.genesistest.presentation.common.GenericAdapter
import com.takeiteasy.vip.genesistest.presentation.common.ViewHolderBinder
import kotlinx.android.synthetic.main.view_ongoing_item.view.*

class OngoingMoviesAdapter : GenericAdapter<Movie>() {

    companion object {
        const val EMPTY = 0
        const val ONGOING = 1
    }


    override fun provideLayout(viewType: Int): Int {
        return when (viewType) {
            ONGOING -> R.layout.view_ongoing_item
            else -> R.layout.view_empty
        }
    }

    override fun provideViewHolder(view: View): RecyclerView.ViewHolder {
        return OngoingMovieViewHolder(view)
    }

    class OngoingMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ViewHolderBinder<Movie> {
        override fun bind(item: Movie) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}