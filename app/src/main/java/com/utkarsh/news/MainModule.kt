package com.utkarsh.news

import android.content.Context
import com.utkarsh.common_utils.Navigator
import com.utkarsh.news.navigation.DefaultNavigator
import com.utkarsh.news.room.AppDatabase
import com.utkarsh.news_data.room.NewsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MainModule {

    @Provides
    @Singleton
    fun provideProvider():Navigator.Provider{
        return DefaultNavigator()
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context):AppDatabase{
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun providesNewsDAO(appDatabase: AppDatabase):NewsDAO{
        return appDatabase.getNewsDao()
    }
}