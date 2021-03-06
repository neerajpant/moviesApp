package com.example.greedygame.ui.movDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.greedygame.R
import com.example.greedygame.model.ProductionCompany
import com.example.greedygame.model.SimilarMovies

class ProductionCompanyAdapter(private val similarMovies: List<ProductionCompany>) :  RecyclerView.Adapter<ProductionCompanyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.layout_production_company, parent, false)
        )
    }

    override fun getItemCount(): Int {
        println("productionCompanyAdapter ${similarMovies.size}")
        return similarMovies.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val similarMovies = similarMovies[position]

        holder.movieName.text = similarMovies.name
        Glide.with(holder.itemView)
            .load(similarMovies.getFullPosterPath())
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_error)
            .into(holder.movieImage)


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.image_production_movies)
        val movieName: TextView = itemView.findViewById(R.id.text_movies)



    }


}