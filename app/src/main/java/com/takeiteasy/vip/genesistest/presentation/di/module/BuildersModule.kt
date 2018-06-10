package com.takeiteasy.vip.genesistest.presentation.di.module

import com.takeiteasy.vip.genesistest.presentation.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity
}