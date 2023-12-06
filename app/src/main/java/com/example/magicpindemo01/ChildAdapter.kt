package com.example.magicpindemo01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.magicpindemo01.model.MovieResult

class ChildAdapter(private val movieList: List<MovieResult>) :
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle = itemView.findViewById<TextView>(R.id.MovieName_Hrz_TextView)

        val moviePoster = itemView.findViewById<ImageView>(R.id.MoviePoster_hrz_ImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.horizontal_card_view, parent, false)
        return ChildViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val currentItem = movieList[position]
        holder.movieTitle.text = currentItem.title

        Glide
            .with(holder.itemView.context)
            .load(Constants.MOVIE_POSTER_PREFIX + currentItem.poster_path)
            .error(R.drawable.error_image)
            .into(holder.moviePoster)

    }
}