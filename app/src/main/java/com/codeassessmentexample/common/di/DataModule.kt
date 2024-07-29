package com.codeassessmentexample.common.di

import com.codeassessmentexample.data.UserRepositoryImpl
import com.codeassessmentexample.domain.repository.UserRepository
import com.codeassessmentexample.data.local.UserDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl)  : UserRepository = userRepositoryImpl
}