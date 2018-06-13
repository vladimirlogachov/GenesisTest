package com.takeiteasy.vip.genesistest.presentation.common

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class GenericPagingAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<T> = mutableListOf()
    private var page: Int = 0
    private var pageSize: Int = 0
    private var isLastPageLoaded: Boolean = true
    private var isPageLoading: Boolean = false

    abstract fun provideViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    fun updateData(data: List<T>) {
        val prevSize = data.size
        this.data.addAll(data)
        notifyItemRangeInserted(prevSize, data.size)
    }

    fun updatePagingInfo(page: Int, pageSize: Int, isLastPageLoaded: Boolean) {
        this.page = page
        this.pageSize = pageSize
        this.isLastPageLoaded = isLastPageLoaded
    }

    fun isEmpty(): Boolean = data.isEmpty()

    fun getPageSize(): Int = pageSize

    fun isPageLoading(): Boolean = isPageLoading

    fun isLastPageLoaded(): Boolean = isLastPageLoaded

    fun nextPage(): Int = page + 1

    fun refresh() {
        data.clear()
        page = 1
        pageSize = 0
        isLastPageLoaded = false
        isPageLoading = false
    }

    fun showPageLoading(show: Boolean) {
        isPageLoading = show

        if (isPageLoading) {
            notifyItemInserted(data.size)
        } else {
            notifyItemRemoved(data.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return provideViewHolder(parent, viewType)
    }

    fun inflateView(parent: ViewGroup, @LayoutRes layoutRes: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    }

    override fun getItemCount(): Int = when {
            isEmpty() -> 1
            isPageLoading -> data.size + 1
            else -> data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? Binder<T>)?.bind(data[position])
    }

    internal interface Binder<T> {
        fun bind(item: T)
    }

    protected class EmptyStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    protected class PageLoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}