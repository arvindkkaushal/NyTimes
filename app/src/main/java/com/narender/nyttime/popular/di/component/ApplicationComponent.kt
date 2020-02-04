package com.narender.nyttime.popular.di.component

import android.app.Application
import com.narender.nyttime.popular.di.module.RetrofitModule
import com.narender.nyttime.popular.di.module.ViewModelFactoryModule
import com.narender.nyttime.popular.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [RetrofitModule::class])
interface ApplicationComponent {

    fun plusActivityComponent(): ActivityComponent

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance
        fun application(application: Application): Builder
    }

}