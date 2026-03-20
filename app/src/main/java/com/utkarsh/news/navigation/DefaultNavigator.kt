package com.utkarsh.news.navigation

import com.utkarsh.common_utils.Activities
import com.utkarsh.common_utils.Navigator
import com.utkarsh.news_presentation.GoToNewsActivity
import com.utkarsh.search_presentation.GoToSearchActivity

class DefaultNavigator: Navigator.Provider {
    override fun getActivities(activities: Activities): Navigator {
        return when(activities){
            Activities.NewsActivity ->{
                GoToNewsActivity
            }
            Activities.SearchActivity -> {
                GoToSearchActivity
            }
        }
    }
}