package com.takeiteasy.vip.genesistest.domain.repository

import com.takeiteasy.vip.genesistest.domain.model.Movie
import com.takeiteasy.vip.genesistest.domain.model.PagingData
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface MoviesRepository {
    fun saveOngoingMovies(movies: List<Movie>): Completable
    fun loadOngoingMovies(releaseDateGte: Date, releaseDateLte: Date, page: Int): Single<PagingData<Movie>>
    fun addMovieToFavorite(movie: Movie): Completable
    fun removeMovieFromFavorite(): Single<List<Movie>>
    fun loadFavoriteMovies(): Single<List<Movie>>
}