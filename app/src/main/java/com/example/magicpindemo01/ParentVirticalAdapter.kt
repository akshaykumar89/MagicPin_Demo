package com.example.magicpindemo01

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.magicpindemo01.databinding.HorizontalRecyclerviewChildBinding
import com.example.magicpindemo01.databinding.VerticalMovieCardViewBinding
import com.example.magicpindemo01.model.MovieResult



class ParentAdapter(private val moviesList: MutableList<MovieResult>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var parentBinding: VerticalMovieCardViewBinding
    private lateinit var childRecyclerViewBinding: HorizontalRecyclerviewChildBinding

    inner class ParentViewHolder(binding: VerticalMovieCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val movieTitle = binding.movieTitleTextView
        val movieDescription = binding.movieDescriptionTextView
        val moviePoster = binding.movieImageView
    }

    inner class HorizontalRecyclerViewHolder(binding: HorizontalRecyclerviewChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val childRecyclerView = binding.childRecyclerViewWidget
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

       return when (viewType) {
            0 -> {
                childRecyclerViewBinding = HorizontalRecyclerviewChildBinding.inflate(
                    inflater, parent, false
                )
                HorizontalRecyclerViewHolder(childRecyclerViewBinding)
            }

            else -> {
                parentBinding = VerticalMovieCardViewBinding.inflate(
                    inflater, parent, false
                )
                ParentViewHolder(parentBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HorizontalRecyclerViewHolder -> {
                val childLayoutManager = LinearLayoutManager(
                    holder.itemView.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                holder.childRecyclerView.layoutManager = childLayoutManager
                val childAdapter = ChildAdapter(moviesList)
                holder.childRecyclerView.adapter = childAdapter
            }

            is ParentViewHolder -> {
                val currentItem = moviesList[position]
                holder.movieTitle.text = currentItem.title
                holder.movieDescription.text = currentItem.overview
                Glide
                    .with(holder.itemView.context)
                    .load(Constants.MOVIE_POSTER_PREFIX + currentItem.poster_path)
                    .error(R.drawable.error_image)
                    .into(holder.moviePoster)
            }
        }
    }

    fun addItems(moviesList: MutableList<MovieResult>?) {
        if (moviesList.isNullOrEmpty()) {
            return
        }

        val currentSize = this.moviesList.size
        Log.e("size", "size of mutable list = $currentSize")
        this.moviesList.addAll(moviesList)
        notifyItemRangeChanged(currentSize, this.moviesList.size - currentSize)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


}