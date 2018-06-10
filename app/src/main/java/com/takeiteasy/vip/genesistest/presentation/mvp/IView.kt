package com.takeiteasy.vip.genesistest.presentation.mvp

interface IView {
    fun showProgress(show: Boolean)
    fun showError(error: String)
}