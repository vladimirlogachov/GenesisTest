package com.takeiteasy.vip.genesistest.presentation.ui.ongoing

import com.takeiteasy.vip.genesistest.data.network.model.Movie
import com.takeiteasy.vip.genesistest.data.network.model.PagingData
import com.takeiteasy.vip.genesistest.usecase.OngoingMoviesUseCase
import com.takeiteasy.vip.genesistest.presentation.mvp.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

class OngoingMoviesPresenterImpl(
        private val useCase: OngoingMoviesUseCase
) : Presenter<OngoingMoviesContract.OngoingMoviesView>(), OngoingMoviesContract.OngoingMoviesPresenter {


    override fun loadOngoingMovies(releaseDateGte: Date, releaseDateLte: Date, page: Int) {
        disposable.add(
                useCase.loadOngoingMovies(releaseDateGte, releaseDateLte, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            if (page > 1) {
                                getView()?.showPageLoading(true)
                            } else {
                                getView()?.showProgress(true)
                            }
                        }
                        .doFinally {
                            if (page > 1) {
                                getView()?.showPageLoading(false)
                            } else {
                                getView()?.showProgress(false)
                            }
                        }
                        .subscribeWith(object : DisposableSingleObserver<PagingData<Movie>>() {
                            override fun onSuccess(t: PagingData<Movie>) {
                                getView()?.showMovies(t.results, t.page, t.getPageSize(), t.isLastPage())
                            }

                            override fun onError(e: Throwable) {
                                getView()?.showError(e.localizedMessage)
                            }
                        })
        )
    }

    override fun addMovieToFavorite(id: Int) {
        disposable.add(
                useCase.addMovieToFavorite(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        )
    }
}