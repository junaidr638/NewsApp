package com.example.newsapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.R
import com.example.newsapp.ui.NewsData

@Composable
fun DetailScreen(navController: NavController,newsData: NewsData) {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold)


        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Navigate to Top News + ${newsData.author} ")
        }

    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(rememberNavController(), NewsData(
        6,
        R.drawable.eggs,
        "Free Press Kashmir",
        " Prices of eggs skyrocket in Kashmir ",
        "Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026. ",
        "13-02-2023 04:33"
    ))
}