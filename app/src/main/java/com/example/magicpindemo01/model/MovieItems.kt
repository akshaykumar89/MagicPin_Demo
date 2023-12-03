package com.example.magicpindemo01.model

data class MovieItems(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)