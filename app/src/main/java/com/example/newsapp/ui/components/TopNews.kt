package com.example.newsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.MockData
import com.example.newsapp.ui.NewsData

@Composable
fun TopNews(navController: NavController) {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        LazyColumn {
            items(MockData.topNewsList) { newsItem ->
                NewsItemUI(
                    newsData = newsItem,
                    onNewsClick = {
                        navController.navigate("details/${newsItem.id}")
                    })
            }
        }
    }

}


@Composable
fun NewsItemUI(newsData: NewsData, onNewsClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .height(230.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onNewsClick()
            }
    ) {
        Image(
            painter = painterResource(id = newsData.image),
            contentDescription = "News Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = newsData.publishedAt, color = Color.White)
            Text(text = newsData.author, color = Color.White, fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.height(35.dp))
            Text(text = newsData.title, fontWeight = FontWeight.ExtraBold, color = Color.White)
            Text(text = newsData.description, fontWeight = FontWeight.SemiBold, color = Color.White)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    NewsItemUI(
        NewsData(
            7,
            com.example.newsapp.R.drawable.breaking_news,
            "Anonymous",
            "Turkey hit by earhtquakes: 7.8 magnitude ",
            "Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026. ",
            "13-02-2023 04:33"
        )
    )
}