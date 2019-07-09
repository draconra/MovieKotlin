package com.draconra.moviekotlin.home.favorites

import com.draconra.moviekotlin.model.Movie

data class FavoritesMoviesViewState(
        val isLoading: Boolean = true,
        val isEmpty: Boolean = true,
        val movies: List<Movie>? = null
)