package com.example.newsapp.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.R
import com.example.newsapp.ui.BottomMenuScreen

@Composable
fun BottomMenu(navController: NavController) {
    val menuList = listOf(
        BottomMenuScreen.Sources,
        BottomMenuScreen.TopNews,
        BottomMenuScreen.Categories
    )

    BottomNavigation(backgroundColor = colorResource(id = R.color.white)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val curentRoute = navBackStackEntry?.destination?.route
        menuList.forEach {
            BottomNavigationItem(
                selected = curentRoute == it.route,
                onClick = {
                          navController.navigate(it.route){
                              navController.graph.startDestinationRoute?.let {
                                  route -> popUpTo(route){
                                      saveState = true
                              }
                              }
                              launchSingleTop = true
                              restoreState = true
                          }

                          },
                label = { Text(text = it.title) },
                unselectedContentColor = Color.Gray,
                selectedContentColor = Color.Green,
                icon = { Icon(imageVector = it.icon, contentDescription = it.title) }
            )
        }
    }
}