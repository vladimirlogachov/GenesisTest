package com.takeiteasy.vip.genesistest.presentation.di.module

import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.takeiteasy.vip.genesistest.data.repository.LoginRepositoryImpl
import com.takeiteasy.vip.genesistest.domain.repository.LoginRepository
import com.takeiteasy.vip.genesistest.domain.usecase.LoginUseCase
import com.takeiteasy.vip.genesistest.presentation.ui.login.LoginContract
import com.takeiteasy.vip.genesistest.presentation.ui.login.LoginPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    fun provideLoginRepository(callbackManager: CallbackManager, loginManager: LoginManager): LoginRepository {
        return LoginRepositoryImpl(callbackManager, loginManager)
    }

    @Provides
    fun provideLoginUseCase(repository: LoginRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    fun provideLoginPresenter(useCase: LoginUseCase): LoginContract.LoginPresenter {
        return LoginPresenterImpl(useCase)
    }
}