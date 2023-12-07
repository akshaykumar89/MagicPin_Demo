package com.example.magicpindemo01.data

import com.example.magicpindemo01.Constants
import com.example.magicpindemo01.model.MovieItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiInterface {
    @GET(Constants.MOVIE_ENDPOINT)
    fun getMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<MovieItems>
}