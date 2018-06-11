package com.takeiteasy.vip.genesistest.data

import com.takeiteasy.vip.genesistest.domain.api.Api
import com.takeiteasy.vip.genesistest.domain.model.Movie
import com.takeiteasy.vip.genesistest.domain.model.PagingResponse
import com.takeiteasy.vip.genesistest.domain.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Single

class MoviesRepositoryImpl(
        private val api: Api
) : MoviesRepository {

    override fun saveOngoingMovies(movies: List<Movie>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadOngoingMovies(): Single<List<Movie>> {
        return api.loadOngoingMovies("", "", 1).flatMap { r: PagingResponse<Movie> -> Single.just(r.results) }
    }

    override fun addMovieToFavorite(movie: Movie): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeMovieFromFavorite(): Single<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadFavoriteMovies(): Single<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}