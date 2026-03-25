package com.utkarsh.search_presentation

import com.utkarsh.search_domain.model.Article

data class SearchState(
    var isLoading: Boolean =false,
    var error:String="",
    var Success:List<Article>?=null
)
