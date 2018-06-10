package com.takeiteasy.vip.genesistest.presentation.mvp

import io.reactivex.disposables.CompositeDisposable

open class Presenter<V : IView> : IPresenter<V>  {

    private var view: V? = null
    protected val disposable: CompositeDisposable = CompositeDisposable()

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        this.disposable.clear()
    }

    protected fun getView(): V? {
        return this.view
    }
}