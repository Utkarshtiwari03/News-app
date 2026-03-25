package com.utkarsh.search_data.network

import com.utkarsh.search_data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SearchApi {


//    https://newsapi.org/v2/everything?q=apple&from=2026-03-24&to=2026-03-24&sortBy=popularity&apiKey=

    @GET("everything")
    suspend fun getSearchArticles(
        @QueryMap map:MutableMap<String,String>
    ):NewsResponse
}