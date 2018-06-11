package com.takeiteasy.vip.genesistest.domain.model

import com.google.gson.annotations.SerializedName

data class PagingResponse<T>(
        val page: Int,
        @SerializedName("total_results")
        val totalResults: Int,
        @SerializedName("total_pages")
        val totalPages: Int,
        val results: List<T>
)