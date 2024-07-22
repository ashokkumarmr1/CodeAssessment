package com.codeassessmentexample.di

import com.codeassessmentexample.data.UserRepositoryImpl
import com.codeassessmentexample.data.repository.UserDataSource
import com.codeassessmentexample.domain.repository.UserRepository
import com.codeassessmentexample.local.UserDataSourceImpl
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

    @Singleton
    @Provides
    fun provideUserDataSource(userDataSourceImpl: UserDataSourceImpl) : UserDataSource = userDataSourceImpl
}