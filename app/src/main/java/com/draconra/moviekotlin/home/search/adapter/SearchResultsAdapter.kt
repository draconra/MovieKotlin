package com.draconra.moviekotlin.home.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.draconra.domain.model.MovieEntity
import com.draconra.moviekotlin.R
import com.draconra.moviekotlin.common.ImageLoader
import com.draconra.moviekotlin.model.Movie
import kotlinx.android.synthetic.main.search_results_adapter_row.view.*


class SearchResultsAdapter constructor(private val imageLoader: ImageLoader,
                                       private val onMovieSelected: (Movie, View) -> Unit) : RecyclerView.Adapter<SearchResultsAdapter.MovieCellViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCellViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(
                R.layout.search_results_adapter_row,
                parent,
                false)
        return MovieCellViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCellViewHolder, position: Int) {
        val movie = movieEntities[position]
        holder.bind(movie, imageLoader, onMovieSelected)
    }

    private var movieEntities: List<Movie> = listOf()
    var query: String? = null

    fun setResults(movieEntities: List<Movie>, query: String?) {
        this.movieEntities = movieEntities
        this.query = query
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movieEntities.size
    }

    class MovieCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieEntity: Movie, imageLoader: ImageLoader, listener: (Movie, View) -> Unit) = with(itemView) {
            title.text = movieEntity.originalTitle
            rating.text = movieEntity.voteAverage.toString()

            movieEntity.overview?.let {
                overview.text = movieEntity.overview
                overview.visibility = View.VISIBLE
            } ?: run {
                overview.visibility = View.GONE
            }

            movieEntity.posterPath?.let {
                image.visibility = View.VISIBLE
                imageLoader.load(it, image)
            } ?: run { image.visibility == View.INVISIBLE }

            setOnClickListener { listener(movieEntity, itemView) }
        }

    }
}