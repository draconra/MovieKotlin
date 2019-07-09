package com.draconra.moviekotlin.home.search

import com.draconra.moviekotlin.model.Movie

data class SearchViewState(
        val isLoading: Boolean = false,
        val movies: List<Movie>? = null,
        val lastSearchedQuery: String? = null,
        val showNoResultsMessage: Boolean = false
)