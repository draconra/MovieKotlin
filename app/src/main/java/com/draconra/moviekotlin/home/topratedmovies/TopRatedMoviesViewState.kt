package com.draconra.moviekotlin.home.topratedmovies

import com.draconra.moviekotlin.model.Movie

data class TopRatedMoviesViewState(
        var showLoading: Boolean = true,
        var movieEntities: List<Movie>? = null
)
