package com.takeiteasy.vip.genesistest.domain.model

import com.google.gson.annotations.SerializedName
import com.takeiteasy.vip.genesistest.BuildConfig
import java.util.*

data class Movie(
        val id: Int,
        val title: String,
        val overview: String,
        val releaseDate: Date,
        @SerializedName("poster_path")
        private val posterPath: String
) {
    fun getPosterUrl(): String {
        return BuildConfig.MOVIES_IMAGE_DB_BASE_URL + posterPath
    }
}