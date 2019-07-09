package com.draconra.moviekotlin.home.popularmovies

import com.draconra.moviekotlin.model.Movie

data class PopularMoviesViewState(
        var showLoading: Boolean = true,
        var movieEntities: List<Movie>? = null
)
