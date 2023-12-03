package com.example.magicpindemo01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.magicpindemo01.model.MovieResult



class ParentAdapter(private val moviesList : List<MovieResult>) : RecyclerView.Adapter<ParentAdapter.parentViewHolder>()  {

    inner class parentViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)
    {
        val movieTitle = itemview.findViewById<TextView>(R.id.movieTitleTextView)
        val movieDiscription = itemview.findViewById<TextView>(R.id.movieDescriptionTextView)
        val moviePoster = itemview.findViewById<ImageView>(R.id.movieImageView)
        val childRecyclerView = itemView.findViewById<RecyclerView>(R.id.childRecyclerViewWidget)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): parentViewHolder {

        if(viewType==0)
        {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_recyclerview_child,parent,false)
            return parentViewHolder(view)
        }
        val view=LayoutInflater.from(parent.context).inflate(R.layout.vertical_movie_card_view,parent,false)
        return parentViewHolder(view)
    }
    override fun onBindViewHolder(holder: parentViewHolder, position: Int) {

              if(position==0)
              {
                  if (holder.childRecyclerView != null) {
                      val childLayoutManager = LinearLayoutManager(
                          holder.itemView.context,
                          LinearLayoutManager.HORIZONTAL,
                          false
                      )
                      holder.childRecyclerView.layoutManager = childLayoutManager
                      val childAdapter = ChildAdapter(moviesList)
                      holder.childRecyclerView.adapter = childAdapter
                  }
              }
        else {
                  val currentItem = moviesList[position]
                  holder.movieTitle.text = currentItem.title
                  holder.movieDiscription.text = currentItem.overview
                  Glide
                      .with(holder.itemView.context)
                      .load(Constants.MoviePosterPrefix + currentItem.poster_path)
                      .error(R.drawable.error_image)
                      .into(holder.moviePoster)
              }

    }
    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }



}