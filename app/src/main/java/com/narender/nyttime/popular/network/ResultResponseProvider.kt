package com.narender.nyttime.popular.network

object ResultResponseProvider {
    fun provideSearchRepository(): ResultService {
        return ResultService(ResultResponseApi.Factory.create())
    }
}