package com.draconra.moviekotlin.base

import android.app.ActivityOptions
import android.util.Pair
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.draconra.moviekotlin.MovieApp
import com.draconra.moviekotlin.R
import com.draconra.moviekotlin.common.AppRouter
import com.draconra.moviekotlin.model.Movie

open class BaseFragment: Fragment() {

    protected fun getMovieApp(): MovieApp = (context?.applicationContext as MovieApp)

    protected fun navigateToMovieDetailsScreen(movie: Movie) {
        var activityOptions: ActivityOptions? = null

        val imageForTransition: View? = view?.findViewById(R.id.image)
        imageForTransition?.let {
            val posterSharedElement: Pair<View, String> = Pair.create(it, getString(R.string.transition_poster))
            activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, posterSharedElement)
        }

        startActivity(AppRouter.goToDetailActivity(context!!,
                movie.id,
                movie.posterPath), activityOptions?.toBundle())

        activity?.overridePendingTransition(0, 0)
    }

    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}