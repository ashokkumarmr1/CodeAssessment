package com.codeassessmentexample.data.local

import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.data.local.dao.UserDAO
import com.codeassessmentexample.data.local.mappers.UserEntityMapper
import com.codeassessmentexample.data.local.model.UserEntity
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userDAO: UserDAO) {
    private val userEntityMapper : UserEntityMapper = UserEntityMapper()

    suspend fun saveUserList(userList : List<User>) =  userDAO.addUserAll(userList.map { userModel -> userEntityMapper.mapFromModel(userModel) })

}