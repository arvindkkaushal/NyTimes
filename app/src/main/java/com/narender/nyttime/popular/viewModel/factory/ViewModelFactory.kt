package com.example.techchallengelalamove.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.narender.nyttime.popular.di.scope.ActivityScope
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
open class ViewModelFactory @Inject constructor(
        private val providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var provider = providers[modelClass]
        if (provider == null) {
            for ((key, value) in providers) {
                if (modelClass.isAssignableFrom(key)) {
                    provider = value
                    break
                }
            }
        }

        if (provider == null) {
            throw IllegalStateException("Unknown View Model $modelClass")
        }

        try {
            return provider.get() as T
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }
}