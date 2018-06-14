package com.takeiteasy.vip.genesistest.data

import com.takeiteasy.vip.genesistest.domain.api.Api
import com.takeiteasy.vip.genesistest.domain.model.Movie
import com.takeiteasy.vip.genesistest.domain.model.PagingData
import com.takeiteasy.vip.genesistest.domain.network.NetworkManager
import com.takeiteasy.vip.genesistest.domain.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class MoviesRepositoryImpl(
        private val api: Api,
        private val dateMapper: DateMapper,
        private val movieDao: MovieDaoImpl,
        private val moviesMapper: MoviesMapper,
        private val networkManager: NetworkManager
) : MoviesRepository {

    private var isReloadRequired: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    override fun subscribeOnFavoriteMoviesChanges(): BehaviorSubject<Boolean> {
        return isReloadRequired
    }

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
        return if (networkManager.isInternetAvailable()) {
              api.loadOngoingMovies(dateMapper.toModel(releaseDateGte), dateMapper.toModel(releaseDateLte), page)
                        .doOnSuccess { movieDao.insertOrUpdate(mapMoviesToEntities(it.results)) }
            } else {
                movieDao.getAllMovies().map { it.map(moviesMapper::toModel) }.map { PagingData(1, it.size, 1, it) }
            }
    }

    override fun addMovieToFavorite(id: Int): Completable
            = Completable.fromAction{ movieDao.addToFavorite(id) }
            .doOnComplete { isReloadRequired.onNext(true) }

    override fun removeMovieFromFavorite(id: Int): Completable
            = Completable.fromAction{ movieDao.removeFromFavorite(id) }

    override fun loadFavoriteMovies(): Single<List<Movie>>
            = movieDao.findFavoriteMovies().map { it.map(moviesMapper::toModel) }
            .doOnSuccess { isReloadRequired.onNext(false) }

}