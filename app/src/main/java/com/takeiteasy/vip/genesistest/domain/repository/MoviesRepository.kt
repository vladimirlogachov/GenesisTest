package com.takeiteasy.vip.genesistest.domain.repository

import com.takeiteasy.vip.genesistest.domain.model.Movie
import io.reactivex.Completable
import io.reactivex.Single

interface MoviesRepository {
    fun saveOngoingMovies(movies: List<Movie>): Completable
    fun loadOngoingMovies(): Single<List<Movie>>
    fun addMovieToFavorite(movie: Movie): Completable
    fun removeMovieFromFavorite(): Single<List<Movie>>
    fun loadFavoriteMovies(): Single<List<Movie>>
}