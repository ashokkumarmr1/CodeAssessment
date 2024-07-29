package com.codeassessmentexample.data

import com.codeassessmentexample.common.core.Resource
import com.codeassessmentexample.data.local.UserDataSourceImpl
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.domain.repository.UserRepository
import com.codeassessmentexample.data.local.model.UserEntity
import com.codeassessmentexample.data.remote.UserRemoteImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteImpl: UserRemoteImpl,
    private val userDataSourceImpl: UserDataSourceImpl) : UserRepository
{
    override suspend fun getUsers(): Flow<Resource<List<User>>> = userRemoteImpl.getUsers()
    override suspend fun saveUser(userList : List<User>) =  userDataSourceImpl.saveUserList(userList)
}