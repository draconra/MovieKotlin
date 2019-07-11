package com.draconra.moviekotlin.home.topratedmovies

import androidx.lifecycle.MutableLiveData
import com.draconra.domain.common.Mapper
import com.draconra.domain.interactor.GetPopularMovies
import com.draconra.domain.interactor.GetTopRatedMovies
import com.draconra.domain.model.MovieEntity
import com.draconra.moviekotlin.base.BaseViewModel
import com.draconra.moviekotlin.common.SingleLiveEvent
import com.draconra.moviekotlin.model.Movie

class TopRatedMoviesViewModel(private val getTopRatedMovies: GetTopRatedMovies,
                              private val movieEntityMovieEntityMapper: Mapper<MovieEntity, Movie>
) :
        BaseViewModel() {

    var viewState: MutableLiveData<TopRatedMoviesViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()

    init {
        viewState.value = TopRatedMoviesViewState()
    }

    fun getTopRatedMovies() {
        addDisposable(getTopRatedMovies.observable()
                .flatMap { movieEntityMovieEntityMapper.observable(it) }
                .subscribe({ movies ->
                    viewState.value?.let {
                        val newState = this.viewState.value?.copy(showLoading = false, movieEntities = movies)
                        this.viewState.value = newState
                        this.errorState.value = null
                    }

                }, {
                    viewState.value = viewState.value?.copy(showLoading = false)
                    errorState.value = it
                }))
    }
}