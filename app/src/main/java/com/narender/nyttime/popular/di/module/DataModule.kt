package com.narender.nyttime.popular.di.module

import com.narender.nyttime.popular.di.scope.ActivityScope
import com.narender.nyttime.popular.network.RemoteData
import com.narender.nyttime.popular.network.RemoteDataImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    @ActivityScope
    fun bindRemoteData(remoteDataImpl: RemoteDataImpl): RemoteData
}