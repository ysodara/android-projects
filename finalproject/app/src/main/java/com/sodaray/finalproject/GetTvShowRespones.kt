package com.sodaray.finalproject

import com.google.gson.annotations.SerializedName

data class GetTvShowRespones (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val tvShow: List<TvShow>,
    @SerializedName("total_pages") val pages: Int
)