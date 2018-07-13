package com.takeiteasy.vip.genesistest.usecase

import com.takeiteasy.vip.genesistest.data.network.model.Movie
import com.takeiteasy.vip.genesistest.data.network.model.PagingData
import com.takeiteasy.vip.genesistest.data.repository.contract.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class OngoingMoviesUseCase(
        private val repository: MoviesRepository
) {
    fun loadOngoingMovies(releaseDateGte: Date, releaseDateLte: Date, page: Int): Single<PagingData<Movie>>
            = repository.loadOngoingMovies(releaseDateGte, releaseDateLte, page)

    fun addMovieToFavorite(id: Int): Completable
            = repository.addMovieToFavorite(id)
}