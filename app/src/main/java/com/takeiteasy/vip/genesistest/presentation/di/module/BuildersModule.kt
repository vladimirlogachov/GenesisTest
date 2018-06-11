package com.takeiteasy.vip.genesistest.presentation.di.module

import com.takeiteasy.vip.genesistest.presentation.ui.login.LoginActivity
import com.takeiteasy.vip.genesistest.presentation.ui.movie.MoviesActivity
import com.takeiteasy.vip.genesistest.presentation.ui.ongoing.OngoingMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginActivity(): LoginActivity
    @ContributesAndroidInjector(modules = [ProfileModule::class])
    abstract fun bindMoviewsActivity(): MoviesActivity
    @ContributesAndroidInjector(modules = [MoviesModule::class])
    abstract fun bindOngoingMoviesFragment(): OngoingMoviesFragment
}