package com.sodaray.assignment4

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {
    private val api : IMovieApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(IMovieApi::class.java)
    }

    fun getNowPlayingMovies(page : Int = 1,
                         onSuccess : (movies : List<Movie>) -> Unit,
                         onError : () -> Unit) {
        api.getNowPlayingMovies(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    val body = response.body()
                    onSuccess.invoke(body!!.movies)

                }
                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.d("Repository", t.toString(), t)
                    onError.invoke()
                }
            })
    }

    fun getLatest(page : Int = 1,
                     onSuccess : (movies : List<Movie>) -> Unit,
                     onError : () -> Unit) {
        api.getLatest(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    val body = response.body()
                    onSuccess.invoke(body!!.movies)
                }
                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.d("Repository", t.toString(), t)
                    onError.invoke()
                }
            })

    }

    fun getUpcoming(page : Int = 1,
                     onSuccess : (movies : List<Movie>) -> Unit,
                     onError : () -> Unit) {
        api.getUpcoming(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    val body = response.body()
                    onSuccess.invoke(body!!.movies)

                }
                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.d("Repository", t.toString(), t)
                    onError.invoke()
                }
            })

    }

}