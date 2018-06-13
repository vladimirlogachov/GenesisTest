package com.takeiteasy.vip.genesistest.presentation.common

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class GenericAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<T> = mutableListOf()

    abstract fun provideLayout(viewType: Int): Int
    abstract fun provideViewHolder(view: View): RecyclerView.ViewHolder

    fun updateData(data: List<T>) {
        val prevSize = data.size
        this.data.addAll(data)
        notifyItemRangeInserted(prevSize, data.size)
    }

    fun refresh() {
        this.data.clear()
    }

    fun isEmpty(): Boolean {
        return data.isEmpty()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return provideViewHolder(inflateView(parent, provideLayout(viewType)))
    }

    private fun inflateView(parent: ViewGroup, @LayoutRes layoutRes: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    }

    override fun getItemCount(): Int {
        return if (isEmpty()) {
            1
        } else {
            data.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolderBinder<*>)?.bind(data[position])
    }
}