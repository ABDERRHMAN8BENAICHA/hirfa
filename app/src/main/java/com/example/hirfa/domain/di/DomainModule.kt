package com.example.hirfa.domain.di

import com.example.hirfa.data.repository.CategoryRepository
import com.example.hirfa.data.repository.CraftsmanRepository
import com.example.hirfa.domain.usecase.AddCraftsmanUseCase
import com.example.hirfa.domain.usecase.GetCategoriesUseCase
import com.example.hirfa.domain.usecase.GetCraftsmenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(categoryRepository: CategoryRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(categoryRepository) // ✅ Pass categoryRepository
    }

    @Provides
    @Singleton
    fun provideGetCraftsmanUseCase(craftsmanRepository: CraftsmanRepository): GetCraftsmenUseCase {
        return GetCraftsmenUseCase(craftsmanRepository)
    }

    @Provides
    @Singleton
    fun provideAddCraftsmanUseCase(craftsmanRepository: CraftsmanRepository): AddCraftsmanUseCase {
        return AddCraftsmanUseCase(craftsmanRepository)
    }
}
