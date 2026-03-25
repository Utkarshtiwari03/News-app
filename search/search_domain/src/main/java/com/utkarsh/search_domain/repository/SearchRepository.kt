package com.utkarsh.search_domain.repository

import com.utkarsh.search_domain.model.Article

interface SearchRepository {

    suspend fun getSearchArticles(map:MutableMap<String,String>):List<Article>

}