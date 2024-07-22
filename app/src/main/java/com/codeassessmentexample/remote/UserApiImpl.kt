package com.codeassessmentexample.remote

import com.codeassessmentexample.remote.modelDTO.UserModel
import com.codeassessmentexample.remote.api.UserApiService
import retrofit2.Response
import javax.inject.Inject

class UserApiImpl @Inject constructor(private val apiService: UserApiService) : UserApi
{
    override suspend fun getUsers(): Response<List<UserModel>> = apiService.getUsers()
}