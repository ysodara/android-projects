package com.sodaray.finalproject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TvShowRepository {

    private val api: TvApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        api = retrofit.create(TvApi::class.java)
    }
    fun getPopularTvShows(
        page: Int = 1,
        onSuccess: (tvShow: List<TvShow>) -> Unit,
        onError: () -> Unit
    ){
        api.getPopularTvShows(page = page)
            .enqueue(object : Callback<GetTvShowRespones> {
                override fun onResponse(
                    call: Call<GetTvShowRespones>,
                    response: Response<GetTvShowRespones>
                ){
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) { onSuccess.invoke(responseBody.tvShow)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke() }
                }
                override fun onFailure(call: Call<GetTvShowRespones>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}