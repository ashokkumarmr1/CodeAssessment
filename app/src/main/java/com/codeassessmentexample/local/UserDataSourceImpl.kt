package com.codeassessmentexample.local

import com.codeassessmentexample.common.Resource
import com.codeassessmentexample.data.repository.UserDataSource
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.local.dao.UserDAO
import com.codeassessmentexample.local.mappers.UserEntityMapper
import com.codeassessmentexample.local.model.UserEntity
import com.codeassessmentexample.remote.modelDTO.UserModel
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userDAO: UserDAO
) : UserDataSource {
    private val userEntityMapper : UserEntityMapper = UserEntityMapper()

    override suspend fun saveUser(userList : List<User>) =  userDAO.addUserAll(userList.map { userModel -> userEntityMapper.mapFromModel(userModel) })

    override fun save(user: UserEntity) =  userDAO.addUser(UserEntity(user.id, user.name, user.email, user.avatar))
}