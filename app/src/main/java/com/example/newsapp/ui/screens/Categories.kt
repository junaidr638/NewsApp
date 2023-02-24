package com.example.newsapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.network.NewsManager
import com.example.newsapp.ui.MockData
import com.example.newsapp.ui.MockData.timeAgo
import com.example.newsapp.ui.models.TopNewsArticles
import com.example.newsapp.ui.models.getAllCategories
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun Categories(onFetchCategory: (String) -> Unit = {}, newsManager: NewsManager) {

    val categories = getAllCategories()

    Column {
        LazyRow {
            items(categories.size) {
                val category = categories[it]
                CategoryTab(
                    category = category.categoryName,
                    onFetchCategory = onFetchCategory,
                    isSelected = newsManager.selectedCategory.value == category
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Test")
        ArticleContent(
            articles = newsManager.categoryNewsResponse.value.articles ?: listOf(
                TopNewsArticles(
                    title = "Big Breaking", author = "Junaid", publishedAt = "2019-11-04T05:35:21"
                ),
                TopNewsArticles(
                    title = "Big Breaking", author = "Junaid", publishedAt = "2019-11-04T05:35:21"
                ),
                TopNewsArticles(
                    title = "Big Breaking", author = "Junaid", publishedAt = "2019-11-04T05:35:21"
                ),
                TopNewsArticles(
                    title = "Big Breaking", author = "Junaid", publishedAt = "2019-11-04T05:35:21"
                ),
                TopNewsArticles(
                    title = "Big Breaking", author = "Junaid", publishedAt = "2019-11-04T05:35:21"
                )
            )
        )
    }

}

@Composable
fun CategoryTab(
    category: String, isSelected: Boolean = false, onFetchCategory: (String) -> Unit
) {

    val background =
        if (isSelected) colorResource(id = R.color.purple_200) else colorResource(id = R.color.purple_700)
    Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 16.dp)
            .clickable { onFetchCategory(category) },
        color = background,
        shape = MaterialTheme.shapes.small

    ) {
        Text(
            text = category,
            color = Color.White,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(8.dp)
        )
    }


}

@Composable
fun ArticleContent(articles: List<TopNewsArticles>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(articles) {
            Card(
                modifier.padding(8.dp),
                border = BorderStroke(width = 2.dp, color = colorResource(id = R.color.purple_500))
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    CoilImage(
                        imageModel = it.urlToImage,
                        modifier = Modifier.size(100.dp),
                        placeHolder = painterResource(R.drawable.breaking_news),
                        error = painterResource(R.drawable.breaking_news)
                    )

                    Column(modifier.padding(4.dp)) {
                        Text(
                            text = it.title ?: "NOT AVAILABLE",
                            fontWeight = FontWeight.Bold,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis

                        )
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(text = it.author ?: "Not Available")
                            Text(
                                text = MockData.stringToDate(
                                    it.publishedAt ?: "2019-11-04T05:35:21"
                                ).timeAgo()
                            )


                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ArticleContentPreview() {

}