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
        private val dateMapper: DateMapper,
        private val movieDao: MovieDaoImpl,
        private val moviesMapper: MoviesMapper
) : MoviesRepository {

    private fun mapMoviesToEntities(movies: List<Movie>): List<MovieEntity> {
        val entities = mutableListOf<MovieEntity>()

        for (movie in movies) {
            entities.add(moviesMapper.toEntity(movie))
        }

        return entities
    }

    override fun saveOngoingMovies(movies: List<Movie>): Completable
            = Completable.fromAction {
        movieDao.insertOrUpdate(mapMoviesToEntities(movies)) }

    override fun loadOngoingMovies(releaseDateGte: Date, releaseDateLte: Date, page: Int): Single<PagingData<Movie>> {
        return api.loadOngoingMovies(dateMapper.toModel(releaseDateGte), dateMapper.toModel(releaseDateLte), page)
    }

    override fun addMovieToFavorite(id: Int): Completable
            = Completable.fromAction{ movieDao.addToFavorite(id) }

    override fun removeMovieFromFavorite(id: Int): Completable
            = Completable.fromAction{ movieDao.removeFromFavorite(id) }

    override fun loadFavoriteMovies(): Single<List<Movie>>
            = movieDao.findFavoriteMovies().map { it.map(moviesMapper::toModel) }

}