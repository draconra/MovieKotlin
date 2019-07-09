package com.draconra.data.utils

import com.draconra.data.entities.DetailsData
import com.draconra.data.entities.MovieData

class TestsUtils {

    companion object {
        fun getMockedMovieData(id: Int, title: String = "MovieData"): MovieData {
            return MovieData(
                    id = id,
                    title = title,
                    backdropPath = "movieData_backdrop",
                    originalLanguage = "movieData_lan",
                    posterPath = "movieData_poster",
                    originalTitle = "Original title of MovieData",
                    releaseDate = "1970-1-1",
                    adult = true,
                    popularity = 10.0,
                    voteAverage = 7.0,
                    voteCount = 100
            )
        }

        fun generateMovieDataList(): List<MovieData> {

            return (0..4).map {
                MovieData(
                        id = it,
                        title = "MovieEntity$it",
                        backdropPath = "",
                        originalLanguage = "",
                        posterPath = "",
                        originalTitle = "",
                        releaseDate = ""
                )
            }
        }

        fun generateDetailsData(id: Int): DetailsData {
            return DetailsData(
                    id = id,
                    title = "Details Data",
                    backdropPath = "detailsData_backdrop",
                    originalLanguage = "detailsData_lan",
                    posterPath = "detailsData_poster",
                    originalTitle = "Original title of DetailsData",
                    releaseDate = "1984-10-27",
                    adult = false,
                    popularity = 2.5,
                    voteAverage = 4.0,
                    voteCount = 102,
                    overview = "Overview"
            )
        }
    }

}