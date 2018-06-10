package com.takeiteasy.vip.genesistest.domain.repository

import com.takeiteasy.vip.genesistest.data.model.MovieDataModel

interface MoviesRepository {
    fun saveMovies(movies: List<MovieDataModel>)
    fun loadMovies(): List<MovieDataModel>
}