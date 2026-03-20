package com.utkarsh.news_data.network

import com.utkarsh.common_utils.Constants
import com.utkarsh.news_data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

//    https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=api_key
    @GET("top-headlines")
    suspend fun getNewArticle(
        @Query("country") country:String=Constants.COUNTRY,
        @Query("category") category:String=Constants.CATEGORY,
        @Query("apiKey") apiKey:String=Constants.API_KEY
    ):NewsResponse
}