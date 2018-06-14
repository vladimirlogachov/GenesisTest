package com.takeiteasy.vip.genesistest.presentation.di.module

import com.takeiteasy.vip.genesistest.App
import com.takeiteasy.vip.genesistest.data.*
import com.takeiteasy.vip.genesistest.domain.api.Api
import com.takeiteasy.vip.genesistest.domain.db.MovieDao
import com.takeiteasy.vip.genesistest.domain.network.NetworkManager
import com.takeiteasy.vip.genesistest.domain.repository.MoviesRepository
import com.takeiteasy.vip.genesistest.domain.usecase.FavoriteMoviesUseCase
import com.takeiteasy.vip.genesistest.domain.usecase.OngoingMoviesUseCase
import com.takeiteasy.vip.genesistest.presentation.ui.favorite.FavoriteMoviesContract
import com.takeiteasy.vip.genesistest.presentation.ui.favorite.FavoriteMoviesPresenterImpl
import com.takeiteasy.vip.genesistest.presentation.ui.ongoing.OngoingMoviesContract
import com.takeiteasy.vip.genesistest.presentation.ui.ongoing.OngoingMoviesPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesModule {
    @Provides
    @Singleton
    fun provideDateMapper(): DateMapper {
        return DateMapper()
    }

    @Provides
    @Singleton
    fun provideMoviesMapper(): MoviesMapper {
        return MoviesMapper()
    }

    @Provides
    @Singleton
    fun provideMovieDao(application: App): MovieDaoImpl {
        return createMovieDao(application)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(api: Api, dateMapper: DateMapper, movieDao: MovieDaoImpl, moviesMapper: MoviesMapper, networkManager: NetworkManager): MoviesRepository {
        return MoviesRepositoryImpl(api, dateMapper, movieDao, moviesMapper, networkManager)
    }

    @Provides
    @Singleton
    fun provideOngoingMoviesUseCase(repository: MoviesRepository): OngoingMoviesUseCase {
        return OngoingMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideOngoingMoviesPresenter(useCase: OngoingMoviesUseCase): OngoingMoviesContract.OngoingMoviesPresenter {
        return OngoingMoviesPresenterImpl(useCase)
    }

    @Provides
    @Singleton
    fun provideFavoriteMoviesUseCase(repository: MoviesRepository): FavoriteMoviesUseCase {
        return FavoriteMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFavoriteMoviesPresenter(useCase: FavoriteMoviesUseCase): FavoriteMoviesContract.FavoriteMoviesPresenter {
        return FavoriteMoviesPresenterImpl(useCase)
    }
}