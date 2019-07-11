package com.draconra.moviekotlin.home.details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.draconra.moviekotlin.R
import com.draconra.moviekotlin.base.BaseActivity
import com.draconra.moviekotlin.common.ImageLoader
import com.draconra.moviekotlin.common.IntentKey.Companion.MOVIE_ID
import com.draconra.moviekotlin.common.IntentKey.Companion.MOVIE_POSTER_URL
import com.draconra.moviekotlin.common.SimpleTransitionEndedCallback
import com.draconra.moviekotlin.home.details.adapter.VideosAdapter
import com.draconra.moviekotlin.model.Video
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.details_overview_section.*
import kotlinx.android.synthetic.main.details_video_section.*
import javax.inject.Inject

class MovieDetailsActivity : BaseActivity() {

    @Inject
    lateinit var factory: MovieDetailsVMFactory
    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var detailsViewModel: MovieDetailsViewModel
    private lateinit var favoriteButton: FloatingActionButton
    private lateinit var videos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        postponeEnterTransition()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE and View.SYSTEM_UI_FLAG_FULLSCREEN
        getMovieApp().createDetailsComponent().inject(this)

        factory.movieId = intent.getIntExtra(MOVIE_ID, 0)
        detailsViewModel = ViewModelProviders.of(this, factory).get(MovieDetailsViewModel::class.java)
        detailsBackButton.setOnClickListener { finish() }
        favoriteButton = detailsFavoriteFab as FloatingActionButton
        favoriteButton.setOnClickListener { detailsViewModel.favoriteButtonClicked() }
        videos = detailsVideosRc as RecyclerView

        val posterUrl = intent.getStringExtra(MOVIE_POSTER_URL)
        posterUrl?.let {
            imageLoader.load(it, detailsPoster)
            startPostponedEnterTransition()
        } ?: run {
            startPostponedEnterTransition()
        }

        window.sharedElementEnterTransition.addListener(SimpleTransitionEndedCallback {
            observeViewState()
        })

        // If we don't have any entering transition
        if (savedInstanceState != null) {
            observeViewState()
        } else {
            detailsViewModel.getMovieDetails()
        }
    }

    private fun observeViewState() {
        detailsViewModel.viewState.observe(this, Observer { viewState -> handleViewState(viewState) })
        detailsViewModel.favoriteState.observe(this, Observer { favorite -> handleFavoriteStateChange(favorite) })
        detailsViewModel.favoriteButtonState.observe(this, Observer { favorite -> handleButtonFavoriteStateChange(favorite) })
        detailsViewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                showToast(throwable.message ?: "")
            }
        })
    }

    private fun onVideoSelected(video: Video) {
        video.url?.let {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(browserIntent)
        }
    }

    private fun handleViewState(state: MovieDetailsViewState?) {
        if (state == null) return

        detailsTitle.text = state.title
        detailsOverview.text = state.overview
        detailsReleaseDate.text = String.format(getString(R.string.release_date_template, state.releaseDate))
        detailsScore.text = if (state.votesAverage == 0.0) getString(R.string.n_a) else state.votesAverage.toString()
        state.genres?.let { detailsTags.tags = state.genres }

        val transition = Slide()
        transition.excludeTarget(detailsPoster, true)
        transition.duration = 750
        TransitionManager.beginDelayedTransition(detailsRootView as ViewGroup, transition)
        detailsTitle.visibility = View.VISIBLE
        detailsReleaseDate.visibility = View.VISIBLE
        detailsScore.visibility = View.VISIBLE
        detailsReleaseDateLayout.visibility = View.VISIBLE
        detailsScoreLayout.visibility = View.VISIBLE
        detailsOverviewSection.visibility = View.VISIBLE
        detailsVideoSection.visibility = View.VISIBLE
        detailsTags.visibility = View.VISIBLE

        state.backdropUrl?.let { imageLoader.load(it, detailsBackdrop) }

        state.videos?.let {
            val videosAdapter = VideosAdapter(it, this::onVideoSelected)
            videos.layoutManager = LinearLayoutManager(this)
            videos.adapter = videosAdapter
        } ?: run {
            detailsVideoSection.visibility = View.GONE
        }
    }

    @SuppressLint("RestrictedApi")
    private fun handleFavoriteStateChange(favorite: Boolean?) {
        if (favorite == null) return
        favoriteButton.visibility = View.VISIBLE
        favoriteButton.setImageDrawable(
                if (favorite)
                    ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_36dp)
                else
                    ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_36dp))
    }

    private fun handleButtonFavoriteStateChange(favorite: Boolean?) {
        if (favorite == null) return
       if(favorite){
           showToast("Add to Favorite")
       }else{
           showToast("Remove from Favorite")
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        getMovieApp().releaseDetailsComponent()
    }

}
