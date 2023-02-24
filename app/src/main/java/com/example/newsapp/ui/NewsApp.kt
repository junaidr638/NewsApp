package com.example.newsapp.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.network.NewsManager
import com.example.newsapp.ui.components.BottomMenu
import com.example.newsapp.ui.components.DetailScreen
import com.example.newsapp.ui.components.TopNews
import com.example.newsapp.ui.models.TopNewsArticles
import com.example.newsapp.ui.screens.Categories
import com.example.newsapp.ui.screens.Sources

@Composable
fun NewsApp() {
    val navController = rememberNavController()
    val scrollState = rememberScrollState()
    MainScreen(navController = navController, scrollState = scrollState)

}


@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState) {
    Scaffold(bottomBar = {
        BottomMenu(navController = navController)
    }) {
        it
        Navigation(navController, scrollState)
    }
}


fun NavGraphBuilder.bottomNavigation(
    navController: NavHostController,
    articles: List<TopNewsArticles>,
    newsManager: NewsManager
) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController, articles = articles)
    }

    composable(BottomMenuScreen.Categories.route) {
        newsManager.getArticlesByCategory("businesss")
        newsManager.onSelectedCategoryChanged("business")
        Categories(newsManager = newsManager,
                   onFetchCategory = {
                       newsManager.onSelectedCategoryChanged(category = it)
                       newsManager.getArticlesByCategory(category = it)
            }
        )
    }


    composable(BottomMenuScreen.Sources.route) {
        Sources()
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    newsManager: NewsManager = NewsManager()
) {

    val articles = newsManager.newsResponse.value.articles
    Log.d("TOP NEWS", "$articles")
    articles?.let {
        NavHost(navController = navController, startDestination = "topNews") {

            bottomNavigation(navController, articles, newsManager)
            composable("topNews") {
                TopNews(navController, articles)
            }
            composable(
                "details/{index}", arguments = listOf(navArgument("index") {
                    type = NavType.IntType
                })
            ) { navBackStackEntry ->
                val index = navBackStackEntry.arguments?.getInt("index")
                index?.let {
                    DetailScreen(articles[index], scrollState, navController)
                }

            }
        }
    }

}