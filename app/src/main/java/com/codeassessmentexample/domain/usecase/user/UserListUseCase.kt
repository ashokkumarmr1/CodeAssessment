package com.codeassessmentexample.domain.usecase.user

import com.codeassessmentexample.common.core.Resource
import com.codeassessmentexample.data.local.model.UserEntity
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserListUseCase @Inject constructor(private val userRepository: UserRepository)
{
    suspend fun getUsers() : Flow<Resource<List<User>>> = userRepository.getUsers()
}