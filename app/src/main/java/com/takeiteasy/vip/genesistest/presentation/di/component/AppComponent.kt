package com.takeiteasy.vip.genesistest.presentation.di.component

import com.takeiteasy.vip.genesistest.App
import com.takeiteasy.vip.genesistest.presentation.di.module.AppModule
import com.takeiteasy.vip.genesistest.presentation.di.module.BuildersModule
import com.takeiteasy.vip.genesistest.presentation.di.module.FacebookModule
import com.takeiteasy.vip.genesistest.presentation.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, FacebookModule::class, NetworkModule::class, BuildersModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }

    fun inject(application: App)
}