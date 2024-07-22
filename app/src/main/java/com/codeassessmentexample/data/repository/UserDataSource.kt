package com.codeassessmentexample.data.repository

import com.codeassessmentexample.common.Resource
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.local.model.UserEntity
import com.codeassessmentexample.remote.modelDTO.UserModel
import kotlinx.coroutines.flow.Flow

interface UserDataSource  {
    suspend fun saveUser(userList : List<User>)
    fun save(user : UserEntity)
}