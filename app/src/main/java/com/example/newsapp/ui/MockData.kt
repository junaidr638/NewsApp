package com.example.newsapp.ui

import android.os.Build
import android.util.Log
import androidx.compose.runtime.Composable
import com.example.newsapp.R
import java.text.SimpleDateFormat
import java.util.*

object MockData {
    val topNewsList = listOf(

        NewsData(
            1,
            R.drawable.supreme_court,
            "N.Srinivisan",
            "Supreme Court upholds Delimitation in J&K ",
            "Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026.Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026.Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026.Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026.Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026.Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026.Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026Kashmir until 2026.Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026.Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026.Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026.Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026.Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026.Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026... ",
            "2023-02-18T05:35:21"
        ),

        NewsData(
            2,
            R.drawable.elon_musk,
            "Junaid",
            "Supreme Court upholds Delimitation in J&K ",
            "Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026. ",
            "2023-02-16T05:35:21"
        ),

        NewsData(
            3,
            R.drawable.murder_police_us,
            "Reuters",
            "Murder in US ",
            "Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026. ",
            "2023-01-04T05:35:21"
        ),

        NewsData(
            4,
            R.drawable.aero_india_us_f35,
            "ANI",
            "Lethal F-35 to feature in AeroIndia 2023 ",
            "Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026. ",
            "2022-11-04T05:35:21"
        ),

        NewsData(
            5,
            R.drawable.sharad_pawar,
            "N.Srinivisan",
            "Sharad Pawar's nephew's jibe at Team Shinde ",
            "Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026. ",
            "2021-11-04T05:35:21"
        ),

        NewsData(
            6,
            R.drawable.eggs,
            "Free Press Kashmir",
            " Prices of eggs skyrocket in Kashmir ",
            "Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026. ",
            "2020-11-04T05:35:21"
        ),

        NewsData(
            7,
            R.drawable.breaking_news,
            "Anonymous",
            "Turkey hit by earhtquakes: 7.8 magnitude ",
            "Centre had earlier told court that the delimitation process can't wait in Jammu and Kashmir until 2026. ",
            "2019-11-04T05:35:21"
        )
    )

    @Composable
    fun getNewsData(newsId: Int): NewsData {
        return topNewsList.first { it.id == newsId }
    }

    fun Date.timeAgo(): String {

        val calendar = Calendar.getInstance()
        calendar.time = this

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)

        val currentCalendar = Calendar.getInstance()

        val currentYear = currentCalendar.get(Calendar.YEAR)
        val currentMonth = currentCalendar.get(Calendar.MONTH)
        val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)

        val currentHour = currentCalendar.get(Calendar.HOUR)
        val currentMinute = currentCalendar.get(Calendar.MINUTE)


        return if (year < currentYear) {
            val interval = currentYear - year
            if (interval == 1) "$interval year ago" else "$interval years ago"
        } else if (month < currentMonth) {
            val interval = currentMonth - month
            if (interval == 1) "$interval month ago" else "$interval months ago"
        } else if (day < currentDay) {
            val interval = currentDay - day
            if (interval == 1) "$interval day ago" else "$interval days ago"
        } else if (hour < currentHour) {
            val interval = currentHour - hour
            if (interval == 1) "$interval hour ago" else "$interval hours ago"
        } else if (minute < currentMinute) {
            val interval = currentMinute - minute
            if (interval == 1) "$interval minute ago" else "$interval minutes ago"
        } else {
            "a moment ago"
        }

    }

    fun stringToDate(publishedAt: String): Date {
        val date =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).parse(publishedAt)
            } else {
                java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).parse(publishedAt)
            }
        Log.d("published","$date")
        return date
    }
}
