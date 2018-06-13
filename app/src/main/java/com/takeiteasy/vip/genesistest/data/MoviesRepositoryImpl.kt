package com.takeiteasy.vip.genesistest.data

import com.takeiteasy.vip.genesistest.domain.api.Api
import com.takeiteasy.vip.genesistest.domain.model.Movie
import com.takeiteasy.vip.genesistest.domain.model.PagingData
import com.takeiteasy.vip.genesistest.domain.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class MoviesRepositoryImpl(
        private val api: Api,
        private val mapper: DateMapper
) : MoviesRepository {

    override fun saveOngoingMovies(movies: List<Movie>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadOngoingMovies(releaseDateGte: Date, releaseDateLte: Date, page: Int): Single<PagingData<Movie>> {
        return api.loadOngoingMovies(mapper.toModel(releaseDateGte), mapper.toModel(releaseDateLte), page)
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