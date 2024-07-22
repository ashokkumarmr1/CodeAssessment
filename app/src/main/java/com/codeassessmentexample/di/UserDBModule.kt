package com.codeassessmentexample.di

import android.content.Context
import com.codeassessmentexample.local.dao.UserDAO
import com.codeassessmentexample.local.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserDBModule {

    @Provides
    @Singleton
    fun provideUserDB(@ApplicationContext context : Context) : UserDatabase {
        return UserDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideUserDAO(userDatabase: UserDatabase) : UserDAO {
        return userDatabase.userDAO()
    }

}