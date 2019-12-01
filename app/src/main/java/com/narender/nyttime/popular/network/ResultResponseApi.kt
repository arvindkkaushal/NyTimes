package com.narender.nyttime.popular.network

import com.narender.nyttime.BuildConfig
import com.narender.nyttime.popular.model.ResultResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * The interface which provides methods to get result of webservices
 */
interface ResultResponseApi {
    /**
     * Get the list of the Aritcle from the API
     */
    @GET("svc/mostpopular/v2/viewed/{page_value}.json")
    fun getPosts(@Path(value = "page_value") page_value: Int, @Query("api-key") api_key: String): Observable<ResultResponse>

    /**
     * Companion object to create the ResultResponseApi
     */
    companion object Factory {
        fun create(): ResultResponseApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(BuildConfig.BASE_URL)
                    .build()

            return retrofit.create(ResultResponseApi::class.java);
        }

    }
}