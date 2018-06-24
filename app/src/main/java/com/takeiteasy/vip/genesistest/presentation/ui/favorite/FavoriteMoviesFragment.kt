package com.takeiteasy.vip.genesistest.presentation.ui.favorite

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.takeiteasy.vip.genesistest.R
import com.takeiteasy.vip.genesistest.data.network.model.Movie
import com.takeiteasy.vip.genesistest.presentation.router.ActivityRouter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FavoriteMoviesFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
        FavoriteMoviesAdapter.FavoriteMoviesInteractionsListener, FavoriteMoviesContract.FavoriteMoviesView {

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteMoviesFragment()
    }

    @Inject
    lateinit var presenter: FavoriteMoviesContract.FavoriteMoviesPresenter
    @Inject
    lateinit var router: ActivityRouter

    lateinit var adapter: FavoriteMoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteMoviesAdapter(this)

        favoriteMovies.adapter = adapter
        favoriteMovies.layoutManager = LinearLayoutManager(context)

        swipeRefresher.setOnRefreshListener(this)
        presenter.loadFavoriteMovies()
        presenter.subscribeOnChanges()
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

    override fun removeMovieToFavorite(movie: Movie) {
        presenter.removeMovieFromFavorite(movie.id)
    }

    override fun share(text: String) {
        router.requestSharing(context, text)
    }

    override fun onRefresh() {
        adapter.refresh()
        presenter.loadFavoriteMovies()
    }

    override fun showMovies(movies: List<Movie>) {
        adapter.updateData(movies)
    }

    override fun showProgress(show: Boolean) {
        swipeRefresher.isRefreshing = show
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun notifyFavoriteListChanged() {
        adapter.refresh()
        presenter.loadFavoriteMovies()
    }
}
