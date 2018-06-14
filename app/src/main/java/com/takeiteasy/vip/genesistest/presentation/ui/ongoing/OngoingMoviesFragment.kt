package com.takeiteasy.vip.genesistest.presentation.ui.ongoing

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.takeiteasy.vip.genesistest.R
import com.takeiteasy.vip.genesistest.domain.model.Movie
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_ongoing_movies.*
import java.util.*
import javax.inject.Inject

class OngoingMoviesFragment : Fragment(), OngoingMoviesContract.OngoingMoviesView,
        SwipeRefreshLayout.OnRefreshListener, OngoingMoviesAdapter.OngoingMoviesInteractionsListener {

    companion object {
        fun newInstance() = OngoingMoviesFragment()
    }

    @Inject
    lateinit var presenter: OngoingMoviesContract.OngoingMoviesPresenter

    lateinit var adapter: OngoingMoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ongoing_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = OngoingMoviesAdapter(this)

        val layoutManager = LinearLayoutManager(context)
        ongoingMovies.adapter = adapter
        ongoingMovies.layoutManager = layoutManager

        ongoingMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!adapter.isPageLoading() && !adapter.isLastPageLoaded()) {
                    Log.d("PAGIING", "isLoading ? ${adapter.isPageLoading()}, isLastPage ? ${adapter.isLastPageLoaded()}")
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= adapter.getPageSize()) {
                        loadOngoingMoviesForTwoWeeks(adapter.nextPage())
                    }
                }
            }
        })

        swipeRefresher.setOnRefreshListener(this)

        loadOngoingMoviesForTwoWeeks(adapter.nextPage())
    }

    private fun loadOngoingMoviesForTwoWeeks(page: Int) {
        val dateTo = Calendar.getInstance()
        val dateSince = Calendar.getInstance()
        dateSince.add(Calendar.WEEK_OF_YEAR, -2)
        presenter.loadOngoingMovies(dateSince.time, dateTo.time, page)
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

    override fun onRefresh() {
        adapter.refresh()
        loadOngoingMoviesForTwoWeeks(adapter.nextPage())
    }

    override fun showMovies(movies: List<Movie>, page: Int, pageSize: Int, isLastPage: Boolean) {
        adapter.updateData(movies)
        adapter.updatePagingInfo(page, pageSize, isLastPage)
    }

    override fun notifyMovieAddedToFavorite() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPageLoading(show: Boolean) {
        ongoingMovies.post { adapter.showPageLoading(show) }
    }

    override fun showProgress(show: Boolean) {
        swipeRefresher.isRefreshing = show
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun addMovieToFavorite(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun share(text: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
