package com.utkarsh.news_domain.use_case

import com.utkarsh.common_utils.network.Resource
import com.utkarsh.news_domain.model.Article
import com.utkarsh.news_domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsArticleUseCase @Inject constructor(private val newsRepository: NewsRepository)  {

    operator fun invoke():Flow<Resource<List<Article>>> = flow{
        emit(Resource.Loading())
        try{
            emit(Resource.Success(newsRepository.getNewsArticles()))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}