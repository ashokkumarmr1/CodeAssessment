package com.codeassessmentexample.domain.usecase

import com.codeassessmentexample.common.Resource
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.domain.repository.UserRepository
import com.codeassessmentexample.local.model.UserEntity
import com.codeassessmentexample.remote.modelDTO.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository)
{
    suspend fun getUsers() : Flow<Resource<List<User>>> = userRepository.getUsers()

    suspend fun saveUsers(userList : List<User>) = userRepository.saveUser(userList)

    fun save(user : UserEntity) = userRepository.save(user)
}