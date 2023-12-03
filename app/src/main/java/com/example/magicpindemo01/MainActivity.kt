package com.example.magicpindemo01

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magicpindemo01.data.TmdbRetrofitObject
import com.example.magicpindemo01.model.MovieItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private lateinit var parentRecyclerView: RecyclerView
//    private lateinit var movieAdapter: ParentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vertical_recyclerview_parent)

         parentRecyclerView = findViewById(R.id.vertical_recyclerView)
        parentRecyclerView.layoutManager=LinearLayoutManager(this)

        val page  = 1

        val call = TmdbRetrofitObject.apiService.GetMovieList(Constants.API_KEY,page)

        call.enqueue(object : Callback<MovieItems> {
            override fun onResponse(call: Call<MovieItems>, response: Response<MovieItems>) {
                if (response.isSuccessful) {
                    val movies = response.body()!!.results
                    parentRecyclerView.adapter=ParentAdapter(movies)

                } else {

                    Log.e("API", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MovieItems>, t: Throwable) {

                Log.e("API", "Network error: ${t.message}")
            }
        })
    }


    }



