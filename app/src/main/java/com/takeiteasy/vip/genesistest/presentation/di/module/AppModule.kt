package com.takeiteasy.vip.genesistest.presentation.di.module

import com.takeiteasy.vip.genesistest.presentation.router.ActivityRouter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideActivityRouter(): ActivityRouter {
        return ActivityRouter()
    }
}