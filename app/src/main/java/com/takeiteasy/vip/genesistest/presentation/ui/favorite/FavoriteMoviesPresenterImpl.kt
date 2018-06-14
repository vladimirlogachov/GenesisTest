package com.takeiteasy.vip.genesistest.presentation.ui.favorite

import com.takeiteasy.vip.genesistest.domain.model.Movie
import com.takeiteasy.vip.genesistest.domain.usecase.FavoriteMoviesUseCase
import com.takeiteasy.vip.genesistest.presentation.mvp.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FavoriteMoviesPresenterImpl(
        private val useCase: FavoriteMoviesUseCase
) : Presenter<FavoriteMoviesContract.FavoriteMoviesView>(), FavoriteMoviesContract.FavoriteMoviesPresenter {
    override fun subscribeOnChanges() {
        disposable.add(
                useCase.subscribeOnChanges()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { if (it) getView()?.notifyFavoriteListChanged() }
        )
    }

    override fun loadFavoriteMovies() {
        disposable.add(
                useCase.loadFavoriteMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { getView()?.showProgress(true) }
                        .doFinally { getView()?.showProgress(false) }
                        .subscribeWith(object : DisposableSingleObserver<List<Movie>>() {
                            override fun onSuccess(t: List<Movie>) {
                                getView()?.showMovies(t)
                            }

                            override fun onError(e: Throwable) {
                                getView()?.showError(e.localizedMessage)
                            }
                        })
        )
    }

    override fun removeMovieFromFavorite(id: Int) {
        disposable.add(
                useCase.removeMovieFromFavorite(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        )
    }

}