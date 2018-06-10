package com.takeiteasy.vip.genesistest.domain.repository

import com.takeiteasy.vip.genesistest.data.model.MovieDataModel
import io.reactivex.Completable
import io.reactivex.Single

interface MoviesRepository {
    fun saveOngoingMovies(movies: List<MovieDataModel>): Completable
    fun loadOngoingMovies(): Single<List<MovieDataModel>>
    fun saveFavoriteMovies(movies: List<MovieDataModel>): Completable
    fun loadFavoriteMovies(): Single<List<MovieDataModel>>
}