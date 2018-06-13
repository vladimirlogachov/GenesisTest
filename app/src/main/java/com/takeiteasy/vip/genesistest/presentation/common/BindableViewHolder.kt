package com.takeiteasy.vip.genesistest.presentation.common

interface ViewHolderBinder<in T> {
    fun bind(item: T)
}