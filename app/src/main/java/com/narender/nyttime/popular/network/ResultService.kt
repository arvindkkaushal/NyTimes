package com.narender.nyttime.popular.network

import com.narender.nyttime.BuildConfig.API_KEY
import com.narender.nyttime.popular.model.ResultResponse

import io.reactivex.Observable

class ResultService(val apiService: ResultResponseApi) {
    fun getArticles(periods: Int): Observable<ResultResponse> {
        return apiService.getPosts(periods, API_KEY)
    }
}