package com.sodaray.finalproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TvApi {
    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String = "4fc0b74ec4025963321615a773ff0292",
        @Query("page") page: Int
    ): Call<GetTvShowRespones>

}