package com.example.greedygame.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.greedygame.R
import com.example.greedygame.databinding.LayoutMovieListBinding
import com.example.greedygame.databinding.LayoutMoviesListBinding
import com.example.greedygame.model.MovieResponse
import com.example.greedygame.model.MovieResponseData
import kotlinx.android.synthetic.main.layout_movies_list.view.*

class MoviesPagingAdapter(private val listener: OnItemClickListener) : PagingDataAdapter<MovieResponse, MoviesPagingAdapter.MovieViewHolder>(MOVIE_COMPARATOR)
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            LayoutMoviesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }



   inner class MovieViewHolder(private val binding: LayoutMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

       init {
           binding.root.setOnClickListener {
               val position = bindingAdapterPosition
               if (position != RecyclerView.NO_POSITION) {
                   val item = getItem(position)
                   if (item != null) {
                       listener.onItemClick(item)
                   }
               }
           }
       }

       fun bind(movie: MovieResponse) {
            binding.apply {
               Glide.with(itemView)
                    .load(movie.getFullProfilePath())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_launcher_background)
                    .into(movieImage)

                textTitle.text = movie.title
                textLanguage.text=movie.original_language
                textReleaseDate.text=movie.release_date
                textVolteAvg.text=movie.vote_average.toString()
            }
        }

    }
    interface OnItemClickListener {
        fun onItemClick(photo: MovieResponse)
    }
    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieResponse>() {
            override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse) =
                oldItem == newItem
        }
    }


}