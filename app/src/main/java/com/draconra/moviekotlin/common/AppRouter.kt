package com.draconra.moviekotlin.common

import android.content.Context
import android.content.Intent
import com.draconra.moviekotlin.common.IntentKey.Companion.MOVIE_ID
import com.draconra.moviekotlin.common.IntentKey.Companion.MOVIE_POSTER_URL
import com.draconra.moviekotlin.home.details.MovieDetailsActivity

class AppRouter {

    companion object{

        fun goToDetailActivity(context: Context?, movieId: Int?, posterUrl: String?): Intent {
            val i = Intent(context, MovieDetailsActivity::class.java)
            i.putExtra(MOVIE_ID, movieId)
            i.putExtra(MOVIE_POSTER_URL, posterUrl)
            return i
        }
    }
}