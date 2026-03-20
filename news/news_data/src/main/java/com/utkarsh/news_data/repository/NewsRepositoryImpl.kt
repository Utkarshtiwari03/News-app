package com.utkarsh.news_data.repository

import com.utkarsh.common_utils.Constants
import com.utkarsh.news_data.mapper.toDomainArticle
import com.utkarsh.news_data.network.NewsApiService
import com.utkarsh.news_domain.model.Article
import com.utkarsh.news_domain.repository.NewsRepository

class NewsRepositoryImpl(private val newsApiService: NewsApiService):NewsRepository {
    override suspend fun getNewsArticles(): List<Article> {
        return newsApiService.getNewArticle().articles.map {
            it.toDomainArticle()
        }
    }
}