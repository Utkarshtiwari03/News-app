package com.utkarsh.news_data.mapper

import com.utkarsh.news_data.model.ArticleDTO
import com.utkarsh.news_domain.model.Article

fun ArticleDTO.toDomainArticle():Article{
    return Article(
        author=this.author?:"author",
        content=this.content?:"Missing content",
        description=this.description?:"No description",
        title=this.title?:"Title",
        urlToImage=this.urlToImage?:"https://static.vecteezy.com/system/resources/thumbnails/057/068/323/small/single-fresh-red-strawberry-on-table-green-background-food-fruit-sweet-macro-juicy-plant-image-photo.jpg"
    )
}