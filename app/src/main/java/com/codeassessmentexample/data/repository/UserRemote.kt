package com.codeassessmentexample.data.repository

import com.codeassessmentexample.common.Resource
import com.codeassessmentexample.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRemote {
    suspend fun getUsers(): Flow<Resource<List<User>>>
}