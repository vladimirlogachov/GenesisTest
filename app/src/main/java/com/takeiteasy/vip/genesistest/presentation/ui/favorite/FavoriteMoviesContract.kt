package com.takeiteasy.vip.genesistest.presentation.ui.favorite

import com.takeiteasy.vip.genesistest.data.network.model.Movie
import com.takeiteasy.vip.genesistest.presentation.mvp.IPresenter
import com.takeiteasy.vip.genesistest.presentation.mvp.IView

interface FavoriteMoviesContract {
    interface FavoriteMoviesView : IView {
        fun notifyFavoriteListChanged()
        fun showMovies(movies: List<Movie>)
    }

    interface FavoriteMoviesPresenter : IPresenter<FavoriteMoviesView> {
        fun loadFavoriteMovies()
        fun subscribeOnChanges()
        fun removeMovieFromFavorite(id: Int)
    }
}