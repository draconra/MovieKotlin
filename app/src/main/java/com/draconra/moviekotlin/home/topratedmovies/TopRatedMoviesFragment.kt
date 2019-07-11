package com.draconra.moviekotlin.home.topratedmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.draconra.moviekotlin.R
import com.draconra.moviekotlin.base.BaseFragment
import com.draconra.moviekotlin.common.ImageLoader
import com.draconra.moviekotlin.home.topratedmovies.adapter.TopRatedMoviesAdapter
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import javax.inject.Inject


class TopRatedMoviesFragment : BaseFragment() {

    @Inject
    lateinit var factory: TopRatedMoviesVMFactory
    @Inject
    lateinit var imageLoader: ImageLoader
    private lateinit var viewModel: TopRatedMoviesViewModel
    private lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMovieApp().createTopRatedComponent().inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(TopRatedMoviesViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.getTopRatedMovies()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.viewState.observe(this, Observer {
            if (it != null) handleViewState(it)
        })
        viewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                showToast(throwable.message ?: "")
            }
        })
    }

    private fun handleViewState(state: TopRatedMoviesViewState) {
        popularMoviesProgress.visibility = if (state.showLoading) View.VISIBLE else View.GONE
        state.movieEntities?.let { topRatedMoviesAdapter.addMovies(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topRatedMoviesAdapter = TopRatedMoviesAdapter(imageLoader) { movie, view ->
            navigateToMovieDetailsScreen(movie)
        }
        recyclerView = popularMoviesRecycleView as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = topRatedMoviesAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        getMovieApp().releaseTopRatedComponent()
    }
}