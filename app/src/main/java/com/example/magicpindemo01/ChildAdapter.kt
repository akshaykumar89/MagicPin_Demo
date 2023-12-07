package com.example.magicpindemo01

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.magicpindemo01.databinding.HorizontalCardViewBinding
import com.example.magicpindemo01.model.MovieResult

class ChildAdapter(private val movieList: List<MovieResult>) :
    RecyclerView.Adapter<ViewHolder>() {

    private lateinit var childCardBinding: HorizontalCardViewBinding

    inner class ChildViewHolder(binding: HorizontalCardViewBinding) : ViewHolder(binding.root) {
        val movieTitle = binding.MovieNameHrzTextView
        val moviePoster = binding.MoviePosterHrzImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        childCardBinding = HorizontalCardViewBinding.inflate(inflater, parent, false)
        return ChildViewHolder(childCardBinding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ChildViewHolder) {

            val currentItem = movieList[position]
            holder.movieTitle.text = currentItem.title

            Glide
                .with(holder.itemView.context)
                .load(Constants.MOVIE_POSTER_PREFIX + currentItem.poster_path)
                .error(R.drawable.error_image)
                .into(holder.moviePoster)
        }
    }
}