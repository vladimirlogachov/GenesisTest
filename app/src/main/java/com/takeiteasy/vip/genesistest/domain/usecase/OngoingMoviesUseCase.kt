package com.takeiteasy.vip.genesistest.domain.usecase

import com.takeiteasy.vip.genesistest.domain.model.Movie
import com.takeiteasy.vip.genesistest.domain.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Single

class OngoingMoviesUseCase(
        private val repository: MoviesRepository
) {
    fun loadOngoingMovies(): Single<List<Movie>> = repository.loadOngoingMovies()

    fun addMovieToFavorite(movie: Movie): Completable = repository.addMovieToFavorite(movie)
}