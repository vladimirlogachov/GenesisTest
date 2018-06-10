package com.takeiteasy.vip.genesistest.presentation.mvp

import io.reactivex.disposables.CompositeDisposable

open class Presenter<V : IView> : IPresenter<V>  {

    private var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

}