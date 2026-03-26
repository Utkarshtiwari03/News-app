package com.utkarsh.search_domain.usecase


import com.utkarsh.common_utils.network.Resource
import com.utkarsh.search_domain.model.Article
import com.utkarsh.search_domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchArticleNewsCase @Inject constructor(private val searchRepository: SearchRepository) {
    operator fun invoke(map:MutableMap<String,String>): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try{
            emit(Resource.Success(searchRepository.getSearchArticles(map)))
        }catch (e:Exception){
            emit(Resource.Error(message = "$e"))
        }
    }
}