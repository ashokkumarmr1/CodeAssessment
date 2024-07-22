package com.codeassessmentexample.data

import com.codeassessmentexample.common.Resource
import com.codeassessmentexample.data.repository.UserDataSource
import com.codeassessmentexample.data.repository.UserRemote
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.domain.repository.UserRepository
import com.codeassessmentexample.local.dao.UserDAO
import com.codeassessmentexample.local.mappers.UserEntityMapper
import com.codeassessmentexample.local.model.UserEntity
import com.codeassessmentexample.remote.modelDTO.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
        private val userRemote : UserRemote,
        private val userDataSource: UserDataSource) : UserRepository
{
    override suspend fun getUsers(): Flow<Resource<List<User>>> = userRemote.getUsers()

    override suspend fun saveUser(userList : List<User>) =  userDataSource.saveUser(userList)

    override fun save(user: UserEntity) =  userDataSource.save(UserEntity(user.id, user.name, user.email, user.avatar))
}