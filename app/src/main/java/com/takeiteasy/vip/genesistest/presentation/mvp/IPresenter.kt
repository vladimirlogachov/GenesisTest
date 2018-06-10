package com.takeiteasy.vip.genesistest.presentation.mvp

interface IPresenter<V : IView> {
    fun attachView(view: V)
    fun detachView()
}