package com.takeiteasy.vip.genesistest.usecase

import com.takeiteasy.vip.genesistest.data.network.model.Movie
import com.takeiteasy.vip.genesistest.data.repository.contract.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class FavoriteMoviesUseCase(
        private val repository: MoviesRepository
) {
    fun loadFavoriteMovies(): Single<List<Movie>>
            = repository.loadFavoriteMovies()

    fun removeMovieFromFavorite(id: Int): Completable
            = repository.removeMovieFromFavorite(id)

    fun subscribeOnChanges(): BehaviorSubject<Boolean>
            = repository.subscribeOnFavoriteMoviesChanges()
}