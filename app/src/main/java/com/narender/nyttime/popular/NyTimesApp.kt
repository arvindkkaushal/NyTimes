package com.narender.nyttime.popular

import android.app.Application
import com.narender.nyttime.popular.di.component.ApplicationComponent
import com.narender.nyttime.popular.di.component.DaggerApplicationComponent

class NyTimesApp : Application() {

    private val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent = appComponent
}