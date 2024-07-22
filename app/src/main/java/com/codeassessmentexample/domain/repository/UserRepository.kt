package com.codeassessmentexample.domain.repository

import com.codeassessmentexample.common.Resource
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.local.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository
{
    suspend fun getUsers(): Flow<Resource<List<User>>>
    suspend fun saveUser(userList : List<User>)
    fun save(user : UserEntity)
}