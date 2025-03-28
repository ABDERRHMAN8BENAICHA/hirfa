package com.example.hirfa.data.di

import com.example.hirfa.data.repository.CategoryRepository
import com.example.hirfa.data.repository.CategoryRepositoryImpl
import com.example.hirfa.data.repository.CraftsmanRepository
import com.example.hirfa.data.repository.CraftsmanRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
    fun provideCraftsmanRepository(): CraftsmanRepository {
        return CraftsmanRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(): CategoryRepository {
        return CategoryRepositoryImpl()
    }
}