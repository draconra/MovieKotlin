package com.draconra.moviekotlin.home.search

import androidx.lifecycle.MutableLiveData
import com.draconra.domain.common.Mapper
import com.draconra.domain.interactor.SearchMovie
import com.draconra.domain.model.MovieEntity
import com.draconra.moviekotlin.base.BaseViewModel
import com.draconra.moviekotlin.common.SingleLiveEvent
import com.draconra.moviekotlin.model.Movie


class SearchViewModel(private val searchMovie: SearchMovie,
                      private val entityMovieEntityMapper: Mapper<MovieEntity, Movie>) : BaseViewModel() {

    var viewState: MutableLiveData<SearchViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()

    init {
        viewState.value = SearchViewState()
    }

    fun search(query: String) {

        errorState.value = null

        if (query.isEmpty()) {
            viewState.value = viewState.value?.copy(
                    isLoading = false,
                    showNoResultsMessage = false,
                    lastSearchedQuery = query,
                    movies = null)
        } else {
            viewState.value = viewState.value?.copy(
                    isLoading = true,
                    showNoResultsMessage = false)

            performSearch(query)
        }
    }

    private fun performSearch(query: String) {
//        Log.d(javaClass.simpleName, "Searching $query")

        addDisposable(searchMovie.search(query)
                .flatMap { entityMovieEntityMapper.observable(it) }
                .subscribe({ movies ->
                    viewState.value = viewState.value?.copy(
                            isLoading = false,
                            movies = movies,
                            lastSearchedQuery = query,
                            showNoResultsMessage = movies.isEmpty())

                }, {
                    viewState.value = viewState.value?.copy(
                            isLoading = false,
                            movies = null,
                            lastSearchedQuery = query
                    )
                    errorState.value = it
                }))
    }


}