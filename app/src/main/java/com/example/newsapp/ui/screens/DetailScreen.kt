package com.example.newsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.R
import com.example.newsapp.ui.MockData
import com.example.newsapp.ui.MockData.stringToDate
import com.example.newsapp.ui.MockData.timeAgo
import com.example.newsapp.ui.NewsData

@Composable
fun DetailScreen(newsData: NewsData, scrollState: ScrollState, navController: NavController) {
    Scaffold(topBar = {

        TopAppBarDetail {
            navController.popBackStack()
        }

    }) {
        it
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold)
            Image(
                painter = painterResource(id = newsData.image),
                contentDescription = ("Cover Image")
            )
            Row {
                IconWithInfo(icon = Icons.Outlined.Edit, newsData.author)
                Spacer(modifier = Modifier.width(30.dp))
                IconWithInfo(icon = Icons.Outlined.DateRange, info = stringToDate(newsData.publishedAt).timeAgo())
            }

            Text(text = newsData.description)

        }
    }

}


@Composable
fun TopAppBarDetail(onBackPressed: () -> Unit) {

    TopAppBar(
        title = {
            Text(text = "Detail Screen", fontWeight = FontWeight.ExtraBold)
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(Icons.Default.ArrowBack, "")
            }

        }
    )
}

@Composable
fun IconWithInfo(icon: ImageVector, info: String) {
    Row {
        Icon(icon, "meta data", tint = Color.Green)
        Text(text = info, fontWeight = FontWeight.Bold)
    }

}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        NewsData(
            6,
            R.drawable.eggs,
            "Free Press Kashmir",
            " Prices of eggs skyrocket in Kashmir ",
            "Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026. ",
            "13-02-2023 04:33"
        ), rememberScrollState(), rememberNavController()
    )
}