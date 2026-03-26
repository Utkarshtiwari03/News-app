package com.utkarsh.search_data.repository

import com.utkarsh.search_data.mapper.toDomainArticle
import com.utkarsh.search_data.network.SearchApi
import com.utkarsh.search_domain.model.Article
import com.utkarsh.search_domain.repository.SearchRepository

class SearchRepositoryImpl(private val searchApi: SearchApi): SearchRepository {
    override suspend fun getSearchArticles(map: MutableMap<String, String>): List<Article> {
        return searchApi.getSearchArticles(map).articles.map {
            it.toDomainArticle()
        }
    }
}