package com.narender.nyttime.popular.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.techchallengelalamove.ui.factory.ViewModelFactory
import com.narender.nyttime.popular.di.ViewModelKey
import com.narender.nyttime.popular.di.scope.ActivityScope
import com.narender.nyttime.popular.viewModel.ResultActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @ActivityScope
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(ResultActivityViewModel::class)
    abstract fun bindActivityViewModel(viewModel: ResultActivityViewModel): ViewModel
}