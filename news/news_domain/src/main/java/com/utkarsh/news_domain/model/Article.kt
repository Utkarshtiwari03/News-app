package com.utkarsh.news_domain.model

data class Article(
    val author: String,
    val content: String,
    val description: String,
//    val publishedAt: String,
//    val source: SourceDTO,
    val title: String,
//    val url: String,
    val urlToImage: String
)
