package com.example.newsapp.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.components.BottomMenu
import com.example.newsapp.ui.components.DetailScreen
import com.example.newsapp.ui.components.TopNews
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


fun NavGraphBuilder.bottomNavigation(navController: NavHostController) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController)
    }

    composable(BottomMenuScreen.Categories.route) {
        Categories()
    }

    composable(BottomMenuScreen.Sources.route) {
        Sources()
    }
}

@Composable
fun Navigation(navController: NavHostController, scrollState: ScrollState) {
    NavHost(navController = navController, startDestination = "topNews") {

        bottomNavigation(navController)
        composable("topNews") { TopNews(navController) }
        composable(
            "details/{newsId}", arguments = listOf(navArgument("newsId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments!!.getInt("newsId")
            val newsData = MockData.getNewsData(newsId = id)

            DetailScreen(newsData, scrollState, navController)
        }
    }
}