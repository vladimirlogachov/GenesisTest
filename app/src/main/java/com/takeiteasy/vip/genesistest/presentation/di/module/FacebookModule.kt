package com.takeiteasy.vip.genesistest.presentation.di.module

import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FacebookModule {
    @Provides
    @Singleton
    fun provideCallbackManager(): CallbackManager {
        return CallbackManager.Factory.create()
    }

    @Provides
    @Singleton
    fun provideLoginManager(): LoginManager {
        return LoginManager.getInstance()
    }
}