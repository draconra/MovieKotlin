package com.draconra.moviekotlin.home.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draconra.domain.common.Mapper
import com.draconra.domain.interactor.GetFavoriteMovies
import com.draconra.domain.model.MovieEntity
import com.draconra.moviekotlin.model.Movie

class FavoriteMoviesVMFactory(private val useCase: GetFavoriteMovies,
                              private val mapper: Mapper<MovieEntity, Movie>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteMoviesViewModel(useCase, mapper) as T
    }

}