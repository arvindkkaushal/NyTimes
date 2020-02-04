package com.narender.nyttime.popular.di.component

import com.narender.nyttime.popular.di.module.DataModule
import com.narender.nyttime.popular.di.module.ViewModelFactoryModule
import com.narender.nyttime.popular.di.scope.ActivityScope
import com.narender.nyttime.popular.view.ResultActivity
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelFactoryModule::class, DataModule::class])
@ActivityScope
interface ActivityComponent {

    fun inject(resutActivity: ResultActivity)
}