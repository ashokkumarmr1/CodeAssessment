package com.codeassessmentexample.domain.repository

import com.codeassessmentexample.common.core.Resource
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.data.local.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository
{
    suspend fun getUsers(): Flow<Resource<List<User>>>
    suspend fun saveUser(userList : List<User>)
}