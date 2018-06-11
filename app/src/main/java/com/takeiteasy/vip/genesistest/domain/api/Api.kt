package com.takeiteasy.vip.genesistest.domain.api

import com.takeiteasy.vip.genesistest.BuildConfig
import com.takeiteasy.vip.genesistest.domain.model.Movie
import com.takeiteasy.vip.genesistest.domain.model.PagingData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface Api {
    @GET("/discover/movie?language=en-US&api_key=" + BuildConfig.MOVIES_DB_V3_API_KEY)
    fun loadOngoingMovies(
        @Query("primary_release_date.gte") gte: Date,
        @Query("primary_release_date.lte") lte: Date,
        @Query("page") page: Int
    ): Single<PagingData<Movie>>
}