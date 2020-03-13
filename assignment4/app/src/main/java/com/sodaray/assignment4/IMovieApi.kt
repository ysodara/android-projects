package com.sodaray.assignment4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieApi {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey : String = "4fc0b74ec4025963321615a773ff0292",
        @Query("page") page : Int
    ) : Call<GetMoviesResponse>

    @GET("movie/top_rated")
    fun getLatest(
        @Query("api_key") apiKey : String = "4fc0b74ec4025963321615a773ff0292",
        @Query("page") page : Int
    ) : Call<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("api_key") apiKey : String = "4fc0b74ec4025963321615a773ff0292",
        @Query("page") page : Int
    ) : Call<GetMoviesResponse>

}