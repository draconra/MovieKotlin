package com.draconra.data.mappers

import com.draconra.data.entities.MovieData
import com.draconra.domain.common.Mapper
import com.draconra.domain.model.MovieEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataEntityMapper @Inject constructor() : Mapper<MovieData, MovieEntity>() {

    override fun mapFrom(from: MovieData): MovieEntity {
        return MovieEntity(
                id = from.id,
                voteCount = from.voteCount,
                voteAverage = from.voteAverage,
                popularity = from.popularity,
                adult = from.adult,
                title = from.title,
                posterPath = from.posterPath,
                originalLanguage = from.originalLanguage,
                backdropPath = from.backdropPath,
                originalTitle = from.originalTitle,
                releaseDate = from.releaseDate,
                overview = from.overview
        )
    }
}
