package com.takeiteasy.vip.genesistest.data.repository.contract

import com.takeiteasy.vip.genesistest.data.network.model.Movie
import com.takeiteasy.vip.genesistest.data.network.model.PagingData
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import java.util.*

interface MoviesRepository {
    fun saveOngoingMovies(movies: List<Movie>): Completable
    fun loadOngoingMovies(releaseDateGte: Date, releaseDateLte: Date, page: Int): Single<PagingData<Movie>>
    fun addMovieToFavorite(id: Int): Completable
    fun removeMovieFromFavorite(id: Int): Completable
    fun loadFavoriteMovies(): Single<List<Movie>>
    fun subscribeOnFavoriteMoviesChanges(): BehaviorSubject<Boolean>
}