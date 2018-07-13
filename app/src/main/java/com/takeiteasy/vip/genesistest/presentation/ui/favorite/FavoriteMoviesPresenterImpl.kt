package com.takeiteasy.vip.genesistest.presentation.ui.favorite

import com.takeiteasy.vip.genesistest.usecase.FavoriteMoviesUseCase
import com.takeiteasy.vip.genesistest.presentation.mvp.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoriteMoviesPresenterImpl(
        private val useCase: FavoriteMoviesUseCase
) : Presenter<FavoriteMoviesContract.FavoriteMoviesView>(), FavoriteMoviesContract.FavoriteMoviesPresenter {
    override fun subscribeOnChanges() {
        disposable.add(
                useCase.subscribeOnChanges()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        //todo: onError is always should be handled to prevent error hiding
                        .subscribe (
                                { if (it) getView()?.notifyFavoriteListChanged() },
                                { e -> e.printStackTrace() }
                        )
        )
    }

    override fun loadFavoriteMovies() {
        disposable.add(
                useCase.loadFavoriteMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        //cool way to handle progress
                        .doOnSubscribe { getView()?.showProgress(true) }
                        .doFinally { getView()?.showProgress(false) }
                        //consider the approach below. it looks more straightforward and concise
                        .subscribe (
                            { movies -> getView()?.showMovies(movies)},
                            { error -> getView()?.showError(error.localizedMessage) }
                        )
        )
    }

    override fun removeMovieFromFavorite(id: Int) {

        disposable.add(
                useCase.removeMovieFromFavorite(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        //todo: onError is always should be handled to prevent error hiding
                        .subscribe(
                                {},
                                { e -> e.printStackTrace() }
                        )
        )
    }
}