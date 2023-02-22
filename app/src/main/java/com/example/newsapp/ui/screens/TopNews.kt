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
import com.example.newsapp.ui.MockData.timeAgo
import com.example.newsapp.ui.NewsData
import com.example.newsapp.ui.models.TopNewsArticles
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun TopNews(navController: NavController, articles: List<TopNewsArticles>) {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        LazyColumn {
            items(articles.size) { index ->
                NewsItemUI(
                    article = articles[index],
                    onNewsClick = {
                        navController.navigate("details/$index")
                    })
            }
        }
    }

}


@Composable
fun NewsItemUI(article: TopNewsArticles, onNewsClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .height(230.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onNewsClick()
            }
    ) {

        CoilImage(
            imageModel = article.urlToImage,
            contentScale = ContentScale.FillBounds,
            contentDescription = article.description,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = MockData.stringToDate(article.publishedAt!!).timeAgo(), color = Color.White)
            Text(text = article.author ?: "Anonymous", color = Color.White, fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.height(35.dp))
            Text(text = article.title ?: "No info", fontWeight = FontWeight.ExtraBold, color = Color.White)
            Text(text = article.description ?: "No info", fontWeight = FontWeight.SemiBold, color = Color.White)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    NewsItemUI(
       article = TopNewsArticles()
    )
}