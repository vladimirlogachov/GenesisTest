package com.takeiteasy.vip.genesistest.domain.model

import com.google.gson.annotations.SerializedName
import com.takeiteasy.vip.genesistest.BuildConfig
import java.util.*

data class Movie(
        val id: Int,
        val title: String,
        val overview: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("poster_path")
        val posterPath: String?,
        val isFavorite: Boolean
) {
    companion object {
        const val NEXT_LINE: String = "\n"
    }

    fun getPosterUrl(): String {
        return BuildConfig.MOVIES_IMAGE_DB_BASE_URL + posterPath
    }

    fun asStringForShare(): String {
        return title + NEXT_LINE + overview + NEXT_LINE + getPosterUrl()
    }
}