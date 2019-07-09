package com.draconra.moviekotlin.home.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.draconra.moviekotlin.R
import com.draconra.moviekotlin.base.BaseFragment
import com.draconra.moviekotlin.common.ImageLoader
import com.draconra.moviekotlin.home.favorites.adapter.FavoriteMoviesAdapter
import kotlinx.android.synthetic.main.fragment_favorite_movies.*
import javax.inject.Inject

class FavoriteMoviesFragment : BaseFragment() {

    @Inject
    lateinit var factory: FavoriteMoviesVMFactory
    @Inject
    lateinit var imageLoader: ImageLoader
    private lateinit var viewModel: FavoriteMoviesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteMoviesAdapter: FavoriteMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMovieApp().createFavoritesComponent().inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(FavoriteMoviesViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.viewState.observe(this, Observer {
            if (it != null) handleViewState(it)
        })
        viewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                showToast(it.message ?: "")
            }
        })
    }

    private fun handleViewState(state: FavoritesMoviesViewState) {
        favoriteMoviesProgress.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        favoriteMoviesEmptyMessage.visibility = if (!state.isLoading && state.isEmpty) View.VISIBLE else View.GONE
        state.movies?.let { favoriteMoviesAdapter.setMovies(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteMoviesAdapter = FavoriteMoviesAdapter(imageLoader) { movie, view ->
            navigateToMovieDetailsScreen(movie)
        }
        recyclerView = favoriteMoviesRecyclerview as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = favoriteMoviesAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        getMovieApp().releaseFavoritesComponent()
    }
}