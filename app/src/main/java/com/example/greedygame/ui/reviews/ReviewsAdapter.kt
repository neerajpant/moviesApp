package com.example.greedygame.ui.reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.greedygame.R
import com.example.greedygame.model.ProductionCompany
import com.example.greedygame.model.ReviewResponse
import com.example.greedygame.ui.movDetail.ProductionCompanyAdapter

class ReviewsAdapter(private var reviews: List<ReviewResponse>) :
    RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.layout_reviews, parent, false)
        )
    }

    override fun getItemCount(): Int {
        println("ReviewsAdapter ${reviews.size}")
        return reviews.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reviews = reviews[position]

        holder.authorName.text = reviews.author_details?.name
        holder.createDate.text = reviews.created_at
        holder.content.text = reviews.content
        Glide.with(holder.itemView)
            .load(reviews.author_details?.avatar_path)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_error)
            .into(holder.movieImage)


    }

    fun addResult(data: List<ReviewResponse>?) {
        this.reviews = data!!

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.tweeter_image)
        val authorName: TextView = itemView.findViewById(R.id.tweeter_name)
        val createDate: TextView = itemView.findViewById(R.id.tweeter_handle)
        val content: TextView = itemView.findViewById(R.id.content)


    }

}