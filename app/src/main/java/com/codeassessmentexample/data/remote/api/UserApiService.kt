package com.codeassessmentexample.data.remote.api

import com.codeassessmentexample.data.remote.modelDTO.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface UserApiService
{
    @GET("users")
    suspend fun getUsers(): Response<List<UserModel>>
}