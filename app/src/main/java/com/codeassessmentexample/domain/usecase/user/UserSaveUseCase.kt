package com.codeassessmentexample.domain.usecase.user

import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.domain.repository.UserRepository
import javax.inject.Inject

class UserSaveUseCase @Inject constructor(private val userRepository: UserRepository)
{
    suspend fun saveUserList(userList : List<User>) = userRepository.saveUser(userList)
}