package com.example.magicpindemo01

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magicpindemo01.data.TmdbRetrofitObject
import com.example.magicpindemo01.model.MovieItems
import com.example.magicpindemo01.model.MovieResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private lateinit var parentRecyclerView: RecyclerView
    private var canLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vertical_recyclerview_parent)

        parentRecyclerView = findViewById(R.id.vertical_recyclerView)
        parentRecyclerView.layoutManager = LinearLayoutManager(this)

        val myAdapter = ParentAdapter(mutableListOf())
        parentRecyclerView.adapter = myAdapter

        var page = 1
        fillData(page, myAdapter)  // Filling up 1st page

        // Pagination starts here
        parentRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                // Invoke when scrolling <=5 last element of Recyclerview
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 5
                    && firstVisibleItemPosition >= 0 && canLoad
                ) {
                    canLoad = false
                    page++
                    Toast.makeText(this@MainActivity, "Reaching end", Toast.LENGTH_SHORT).show()
                    fillData(page, myAdapter)


                }
            }

        })

    }

    private fun fillData(page: Int, myAdapter: ParentAdapter) {


        val call = TmdbRetrofitObject.apiService.GetMovieList(Constants.API_KEY, page)


        call.enqueue(object : Callback<MovieItems> {
            override fun onResponse(call: Call<MovieItems>, response: Response<MovieItems>) {
                if (response.isSuccessful) {
                    val newMovies = response.body()!!.results
                    Log.e("size", " size of updated ${newMovies.size} and page = ${page}")
                    myAdapter.addItems(newMovies as MutableList<MovieResult>)
                    canLoad=true

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



