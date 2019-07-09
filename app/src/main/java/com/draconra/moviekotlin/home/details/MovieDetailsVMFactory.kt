package com.draconra.moviekotlin.home.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draconra.domain.common.Mapper
import com.draconra.domain.interactor.CheckFavoriteStatus
import com.draconra.domain.interactor.GetMovieDetails
import com.draconra.domain.interactor.RemoveFavoriteMovie
import com.draconra.domain.interactor.SaveFavoriteMovie
import com.draconra.domain.model.MovieEntity
import com.draconra.moviekotlin.model.Movie

class MovieDetailsVMFactory(
        private val getMovieDetails: GetMovieDetails,
        private val saveFavoriteMovie: SaveFavoriteMovie,
        private val removeFavoriteMovie: RemoveFavoriteMovie,
        private val checkFavoriteStatus: CheckFavoriteStatus,
        private val mapper: Mapper<MovieEntity, Movie>) : ViewModelProvider.Factory {

    var movieId: Int = -1

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(
                getMovieDetails,
                saveFavoriteMovie,
                removeFavoriteMovie,
                checkFavoriteStatus,
                mapper,
                movieId) as T //TODO: solve casting issue
    }

}