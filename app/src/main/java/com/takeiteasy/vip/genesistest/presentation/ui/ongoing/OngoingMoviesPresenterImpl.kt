package com.takeiteasy.vip.genesistest.presentation.ui.ongoing

import com.takeiteasy.vip.genesistest.domain.model.Movie
import com.takeiteasy.vip.genesistest.domain.model.PagingData
import com.takeiteasy.vip.genesistest.domain.usecase.OngoingMoviesUseCase
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
                        .doOnSubscribe { getView()?.showProgress(true) }
                        .doFinally { getView()?.showProgress(false) }
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

    override fun addMovieToFavorite(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}