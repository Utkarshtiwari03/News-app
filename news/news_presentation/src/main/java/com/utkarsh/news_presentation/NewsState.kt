package com.utkarsh.news_presentation

import com.utkarsh.news_domain.model.Article

data class NewsState(
    val Loading:Boolean=false,
    val Error:String="",
    val data:List<Article>?=null
)
