package com.codeassessmentexample.data.remote

import com.codeassessmentexample.data.remote.modelDTO.UserModel
import retrofit2.Response

interface UserApi
{
    suspend fun getUsers(): Response<List<UserModel>>
}
