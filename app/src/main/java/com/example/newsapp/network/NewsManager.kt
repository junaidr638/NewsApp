package com.example.newsapp.network

import android.util.Log
import androidx.compose.runtime.*
import com.example.newsapp.ui.models.ArticleCategory
import com.example.newsapp.ui.models.TopNewsResponse
import com.example.newsapp.ui.models.getSpecificCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsManager {
    private val _newsResponse = mutableStateOf(TopNewsResponse())
    val newsResponse: State<TopNewsResponse>
        @Composable get() = remember {
            _newsResponse
        }

    private val _categoryNewsResponse = mutableStateOf(TopNewsResponse())
    val categoryNewsResponse : MutableState<TopNewsResponse>
       @Composable get() = remember {
           _categoryNewsResponse
       }

    val selectedCategory:MutableState<ArticleCategory?> = mutableStateOf(null)

    init {
        getArticles()
        getArticlesByCategory("business")
    }


    private fun getArticles(){

        val service = Api.retrofitService.getTopArticles("in",Api.API_KEY)
        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful){
                    _newsResponse.value = response.body()!!
                    Log.d("NEWS","${response.body()}")
                }else {
                    Log.d("NEWS ERROR","${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.e("NEWS ERROR","${t.printStackTrace()}")
            }

        })
    }


     fun getArticlesByCategory(category:String){

        val service = Api.retrofitService.getsArticlesByCategory("in",category,Api.API_KEY)
        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful){
                    _categoryNewsResponse.value = response.body()!!
                    Log.d("CAT NEWS SUCCESS","${response.body()}")
                }else {
                    Log.d("CAT NEWS ERROR","${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.e("CAT NEWS ERROR","${t.printStackTrace()}")
            }

        })
    }


    fun onSelectedCategoryChanged(category:String){
        val newCategory = getSpecificCategory(category)
        selectedCategory.value = newCategory
    }
}
