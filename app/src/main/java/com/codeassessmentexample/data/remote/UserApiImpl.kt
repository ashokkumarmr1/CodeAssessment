package com.codeassessmentexample.data.remote

import com.codeassessmentexample.data.remote.modelDTO.UserModel
import com.codeassessmentexample.data.remote.api.UserApiService
import retrofit2.Response
import javax.inject.Inject

class UserApiImpl @Inject constructor(private val apiService: UserApiService) : UserApi
{
    override suspend fun getUsers(): Response<List<UserModel>> = apiService.getUsers()
}