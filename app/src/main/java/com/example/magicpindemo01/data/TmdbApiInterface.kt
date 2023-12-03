package com.example.magicpindemo01.data

import com.example.magicpindemo01.Constants
import com.example.magicpindemo01.model.MovieItems
import com.example.magicpindemo01.model.MovieResult
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiInterface {
    @GET(Constants.MOVIE_ENDPOINT)
     fun GetMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<MovieItems>
}