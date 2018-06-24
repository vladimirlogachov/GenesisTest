package com.takeiteasy.vip.genesistest.data.network.model

import com.google.gson.annotations.SerializedName

data class PagingData<T>(
        val page: Int,
        @SerializedName("total_results")
        val totalResults: Int,
        @SerializedName("total_pages")
        val totalPages: Int,
        val results: List<T>
) {
    fun isLastPage(): Boolean {
        return page == totalPages
    }

    fun getPageSize(): Int {
        return results.size
    }
}