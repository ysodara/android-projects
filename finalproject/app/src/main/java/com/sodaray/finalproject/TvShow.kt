package com.sodaray.finalproject

import com.google.gson.annotations.SerializedName

data class TvShow (

    @SerializedName("id") val id: Long,
    @SerializedName("original_name") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("episode_count") val episodeCount: String
)