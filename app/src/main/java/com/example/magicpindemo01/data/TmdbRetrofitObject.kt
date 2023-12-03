package com.example.magicpindemo01.data

import com.example.magicpindemo01.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TmdbRetrofitObject {

    val apiService: TmdbApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApiInterface::class.java)
    }
}