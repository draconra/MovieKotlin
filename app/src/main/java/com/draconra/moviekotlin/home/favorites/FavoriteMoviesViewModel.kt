package com.draconra.moviekotlin.home.favorites

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.draconra.domain.common.Mapper
import com.draconra.domain.interactor.GetFavoriteMovies
import com.draconra.domain.model.MovieEntity
import com.draconra.moviekotlin.base.BaseViewModel
import com.draconra.moviekotlin.common.SingleLiveEvent
import com.draconra.moviekotlin.model.Movie

class FavoriteMoviesViewModel(private val getFavoriteMovies: GetFavoriteMovies,
                              private val movieEntityMovieMapper: Mapper<MovieEntity, Movie>) : BaseViewModel() {

    var viewState: MutableLiveData<FavoritesMoviesViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()

    init {
        val viewState = FavoritesMoviesViewState()
        this.viewState.value = viewState
    }

    fun getFavorites() {
        getFavoriteMovies.observable()
                .flatMap { movieEntityMovieMapper.observable(it) }
                .subscribe({ movies ->
                    val newViewState = viewState.value?.copy(
                            isEmpty = movies.isEmpty(),
                            isLoading = false,
                            movies = movies)
                    viewState.value = newViewState
                    errorState.value = null

                }, {
                    viewState.value = viewState.value?.copy(isLoading = false, isEmpty = false)
                    errorState.value = it

                })
    }
}