package com.codeassessmentexample.di

import com.codeassessmentexample.common.constant
import com.codeassessmentexample.data.repository.UserRemote
import com.codeassessmentexample.remote.UserApi
import com.codeassessmentexample.remote.UserApiImpl
import com.codeassessmentexample.remote.api.UserApiService
import com.codeassessmentexample.remote.repository.UserRemoteImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule
{
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(constant.BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): UserApiService = retrofit.create(UserApiService::class.java)

    @Singleton
    @Provides
    fun providesRepository(userHelperImpl: UserApiImpl)  : UserApi = userHelperImpl

    @Singleton
    @Provides
    fun provideUserRemote(userRemoteImpl: UserRemoteImpl)  : UserRemote = userRemoteImpl
}