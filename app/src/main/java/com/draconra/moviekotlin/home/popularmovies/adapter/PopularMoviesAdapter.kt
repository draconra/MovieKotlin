package com.draconra.moviekotlin.home.popularmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.draconra.moviekotlin.R
import com.draconra.moviekotlin.common.ImageLoader
import com.draconra.moviekotlin.model.Movie
import kotlinx.android.synthetic.main.popular_movies_adapter_cell.view.*


class PopularMoviesAdapter constructor(private val imageLoader: ImageLoader,
                                       private val onMovieSelected: (Movie, View) -> Unit) : RecyclerView.Adapter<PopularMoviesAdapter.MovieCellViewHolder>() {


    private val movieEntities: MutableList<Movie> = mutableListOf()

    override fun getItemCount(): Int {
        return movieEntities.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCellViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.popular_movies_adapter_cell, parent, false)
        return MovieCellViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCellViewHolder, position: Int) {
        val movie = movieEntities[position]
        holder.bind(movie, imageLoader, onMovieSelected)
    }

    fun addMovies(movieEntities: List<Movie>) {
        this.movieEntities.addAll(movieEntities)
        notifyDataSetChanged()
    }

    class MovieCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, imageLoader: ImageLoader, listener: (Movie, View) -> Unit) = with(itemView) {
            title.text = movie.originalTitle
            movie.posterPath?.let { imageLoader.load(it, image) }
            setOnClickListener { listener(movie, itemView) }
        }
    }
}