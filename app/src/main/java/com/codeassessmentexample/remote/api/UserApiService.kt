package com.codeassessmentexample.remote.api

import com.codeassessmentexample.remote.modelDTO.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface UserApiService
{
    @GET("users")
    suspend fun getUsers(): Response<List<UserModel>>
}