package com.littletree.thtplay.spots

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by frank.zhang on 2017/8/18.
 */
interface SpotsService {

    @GET("ListSpots")
    fun getSpotsList(@Query("page_num") page_num: Int, @Query("page_size") page_size: Int): Observable<SpotsListEntity>

    /**
     * Companion object to create the SpotsService
     */
    companion object Factory {
        private val spots_url = "http://tianhetan.gzxiaoi.com:9000/api/spots/wx/"

        fun create(): SpotsService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(spots_url)
                    .build()
            return retrofit.create(SpotsService::class.java)
        }
    }
}