package com.draconra.domain.common

import com.draconra.domain.model.MovieEntity
import com.draconra.domain.model.MovieDetailsEntity
import com.draconra.domain.model.ReviewEntity
import com.draconra.domain.model.VideoEntity


class DomainTestUtils {

    companion object {
        fun getTestMovieEntity(id: Int): MovieEntity {
            return MovieEntity(
                    id = id,
                    title = "MovieEntity$id",
                    originalTitle = "MovieEntity$id",
                    backdropPath = "",
                    posterPath = "",
                    originalLanguage = "",
                    releaseDate = "")
        }

        fun getTestMovieEntityWithDetails(id: Int): MovieEntity {
            val movieEntity = getTestMovieEntity(id)
            movieEntity.details = MovieDetailsEntity(
                    overview = "Overview goes here $id",
                    videos = (0..2).map {
                        VideoEntity(
                                "video-$it",
                                "VideoEntity$it",
                                "key-$it")
                    },
                    homepage = "http://www.test.$id.org",
                    tagline = "I'm $id tag line!",
                    reviews = (0..4).map {
                        ReviewEntity(
                                "review-$it",
                                "Author$it",
                                "My score =$it")
                    }
            )
            return movieEntity
        }

        fun generateMovieEntityList(): List<MovieEntity> {
            return (0..4).map { getTestMovieEntity(it) }
        }
    }
}