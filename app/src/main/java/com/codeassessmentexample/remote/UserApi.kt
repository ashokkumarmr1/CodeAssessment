package com.codeassessmentexample.remote

import com.codeassessmentexample.remote.modelDTO.UserModel
import retrofit2.Response

interface UserApi
{
    suspend fun getUsers(): Response<List<UserModel>>
}
