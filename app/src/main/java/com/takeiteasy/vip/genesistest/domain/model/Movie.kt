package com.takeiteasy.vip.genesistest.domain.model

import com.google.gson.annotations.SerializedName
import com.takeiteasy.vip.genesistest.BuildConfig

data class Movie(
        private val id: Int,
        private val title: String,
        private val overview: String,
        @SerializedName("poster_path")
        private val posterPath: String
) {
    fun getPosterUrl(): String {
        return BuildConfig.MOVIES_IMAGE_DB_BASE_URL + posterPath
    }
}