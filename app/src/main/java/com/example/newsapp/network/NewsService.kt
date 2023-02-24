package com.example.newsapp.network

import com.example.newsapp.ui.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun getTopArticles(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<TopNewsResponse>

    @GET("top-headlines")
    fun getsArticlesByCategory(
        @Query("country")country:String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<TopNewsResponse>
}