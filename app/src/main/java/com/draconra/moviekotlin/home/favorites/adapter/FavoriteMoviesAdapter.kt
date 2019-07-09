package com.draconra.moviekotlin.home.favorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.draconra.domain.model.MovieEntity
import com.draconra.moviekotlin.R
import com.draconra.moviekotlin.common.ImageLoader
import com.draconra.moviekotlin.model.Movie
import kotlinx.android.synthetic.main.favorite_movies_adapter_row.view.*


class FavoriteMoviesAdapter constructor(private val imageLoader: ImageLoader,
                                        private val onMovieSelected: (Movie, View) -> Unit) : RecyclerView.Adapter<FavoriteMoviesAdapter.MovieCellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.favorite_movies_adapter_row,
                parent,
                false)
        return MovieCellViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCellViewHolder, position: Int) {
        val movie = movieEntities[position]
        holder.bind(movie, imageLoader, onMovieSelected)
    }

    private var movieEntities: List<Movie> = listOf()

    override fun getItemCount(): Int {
        return movieEntities.size
    }

    fun setMovies(movie: List<Movie>) {
        this.movieEntities = movie
        notifyDataSetChanged()
    }

    class MovieCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieEntity: Movie, imageLoader: ImageLoader, listener: (Movie, View) -> Unit) = with(itemView) {
            title.text = movieEntity.originalTitle
            movieEntity.posterPath?.let { imageLoader.load(it, image) }

            movieEntity.overview?.let {
                overview.text = movieEntity.overview
                overview.visibility = View.VISIBLE
            } ?: run {
                overview.visibility = View.GONE
            }
            setOnClickListener { listener(movieEntity, itemView) }
        }

    }
}