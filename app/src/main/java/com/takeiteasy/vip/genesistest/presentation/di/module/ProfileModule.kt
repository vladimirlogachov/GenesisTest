package com.takeiteasy.vip.genesistest.presentation.di.module

import com.takeiteasy.vip.genesistest.data.ProfileRepositoryImpl
import com.takeiteasy.vip.genesistest.domain.repository.ProfileRepository
import com.takeiteasy.vip.genesistest.domain.usecase.ProfileUseCase
import com.takeiteasy.vip.genesistest.presentation.ui.movie.ProfileContract
import com.takeiteasy.vip.genesistest.presentation.ui.movie.ProfilePresenterImpl
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {
    @Provides
    fun provideProfileRepository(): ProfileRepository {
        return ProfileRepositoryImpl()
    }

    @Provides
    fun provideLoadProfileUseCase(repository: ProfileRepository): ProfileUseCase {
        return ProfileUseCase(repository)
    }

    @Provides
    fun provideProfilePresenter(useCase: ProfileUseCase): ProfileContract.ProfilePresenter {
        return ProfilePresenterImpl(useCase)
    }
}