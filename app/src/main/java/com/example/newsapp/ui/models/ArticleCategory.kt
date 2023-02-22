package com.example.newsapp.ui.models
import com.example.newsapp.ui.models.ArticleCategory.*

enum class ArticleCategory(val categoryName: String) {
    BUSINESS("business"),
    EDUCATION("education"),
    GENERAL("general"),
    HEALTH("health"),
    SPORTS("sports"),
    SCIENCE("science"),
    TECHNOLOGY("technology")
}

fun getAllCategories():List<ArticleCategory>{
    return listOf(
        BUSINESS,
        EDUCATION,
        GENERAL,
        HEALTH,
        SPORTS,
        SCIENCE,
        TECHNOLOGY
    )
}

fun getSpecificCategory(category:String):ArticleCategory?{
    val map = getAllCategories().associateBy { it.categoryName }
    return map[category]
}