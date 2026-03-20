package com.utkarsh.news_domain.repository

import com.utkarsh.news_domain.model.Article

interface NewsRepository {

    suspend fun getNewsArticles():List<Article>
}