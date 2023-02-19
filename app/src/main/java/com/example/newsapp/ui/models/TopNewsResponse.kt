package com.example.newsapp.ui.models

data class TopNewsResponse(
    val status:String? = null,
    val totalResults:Int? = null,
    val articles:List<TopNewsArticles>? = null
)
