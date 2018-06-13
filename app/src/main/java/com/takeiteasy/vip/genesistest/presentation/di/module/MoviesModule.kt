package com.takeiteasy.vip.genesistest.presentation.di.module

import com.takeiteasy.vip.genesistest.data.DateMapper
import com.takeiteasy.vip.genesistest.data.MoviesRepositoryImpl
import com.takeiteasy.vip.genesistest.domain.api.Api
import com.takeiteasy.vip.genesistest.domain.repository.MoviesRepository
import com.takeiteasy.vip.genesistest.domain.usecase.OngoingMoviesUseCase
import com.takeiteasy.vip.genesistest.presentation.ui.ongoing.OngoingMoviesContract
import com.takeiteasy.vip.genesistest.presentation.ui.ongoing.OngoingMoviesPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class MoviesModule {
    @Provides
    fun provideDateMapper(): DateMapper {
        return DateMapper()
    }

    @Provides
    fun provideMoviesRepository(api: Api, mapper: DateMapper): MoviesRepository {
        return MoviesRepositoryImpl(api, mapper)
    }

    @Provides
    fun provideOngoingMoviesUseCase(repository: MoviesRepository): OngoingMoviesUseCase {
        return OngoingMoviesUseCase(repository)
    }

    @Provides
    fun provideOngoingMoviesPresenter(useCase: OngoingMoviesUseCase): OngoingMoviesContract.OngoingMoviesPresenter {
        return OngoingMoviesPresenterImpl(useCase)
    }
}