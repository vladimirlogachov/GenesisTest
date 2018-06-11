package com.takeiteasy.vip.genesistest.presentation.ui.ongoing

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.takeiteasy.vip.genesistest.R
import com.takeiteasy.vip.genesistest.domain.model.Movie
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class OngoingMoviesFragment : Fragment(), OngoingMoviesContract.OngoingMoviesView {

    companion object {
        fun newInstance() =
                OngoingMoviesFragment()
    }

    @Inject
    lateinit var presenter: OngoingMoviesContract.OngoingMoviesPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ongoing_movies, container, false)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        presenter.attachView(this)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detachView()
    }


    override fun showMovies(movies: List<Movie>, page: Int, pageSize: Int, isLastPage: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notifyMovieAddedToFavorite() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
