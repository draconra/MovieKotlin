package com.draconra.moviekotlin.home.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.draconra.domain.model.ReviewEntity
import com.draconra.moviekotlin.R
import com.draconra.moviekotlin.model.Review
import kotlinx.android.synthetic.main.cell_reviews_adapter.view.*


class ReviewsAdapter(private val reviewEntities: List<Review>) : RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_reviews_adapter, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder?.bind(reviewEntities[position])
    }

    override fun getItemCount(): Int {
        return reviewEntities.size
    }

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(reviewEntity: Review) {
            with(itemView) {
                reviewsAdapterContent.text = "\"${reviewEntity.content}\""
                reviewsAdapterAuthor.text = reviewEntity.author
            }
        }

    }
}