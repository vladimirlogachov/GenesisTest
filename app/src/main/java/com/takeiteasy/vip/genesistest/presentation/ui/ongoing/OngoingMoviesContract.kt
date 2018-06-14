package com.takeiteasy.vip.genesistest.presentation.ui.ongoing

import com.takeiteasy.vip.genesistest.domain.model.Movie
import com.takeiteasy.vip.genesistest.presentation.mvp.IPresenter
import com.takeiteasy.vip.genesistest.presentation.mvp.IView
import java.util.*

interface OngoingMoviesContract {
    interface OngoingMoviesView : IView {
        fun showMovies(movies: List<Movie>, page: Int, pageSize: Int, isLastPage: Boolean)
        fun showPageLoading(show: Boolean)
        fun notifyMovieAddedToFavorite()
    }

    interface OngoingMoviesPresenter : IPresenter<OngoingMoviesView> {
        fun loadOngoingMovies(releaseDateGte: Date, releaseDateLte: Date, page: Int)
        fun addMovieToFavorite(movie: Movie)
    }
}