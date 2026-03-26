package com.utkarsh.search_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utkarsh.common_utils.network.Resource
import com.utkarsh.search_domain.usecase.GetSearchArticleNewsCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchArticleNewsCase: GetSearchArticleNewsCase):ViewModel() {

    private val _searchArticles= MutableStateFlow(SearchState())
    val searchArticles: StateFlow<SearchState> =_searchArticles


    fun getSearchArticles(map:MutableMap<String,String>){

        getSearchArticleNewsCase.invoke(map).onEach {
            when(it){
                is Resource.Loading -> {
                    _searchArticles.value=SearchState(isLoading = true)
                }
                is Resource.Error -> {
                    _searchArticles.value=SearchState(error = it.message)
                }
                is Resource.Success -> {
                    _searchArticles.value=SearchState(Success = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}