package com.codeassessmentexample.remote.repository

import com.codeassessmentexample.common.Resource
import com.codeassessmentexample.data.repository.UserRemote
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.remote.UserApi
import com.codeassessmentexample.remote.mappers.UserModelMapper
import com.codeassessmentexample.remote.modelDTO.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRemoteImpl  @Inject constructor(
    private val userHelper: UserApi,
    private val userModelMapper: UserModelMapper) : UserRemote
{
    lateinit var response : List<UserModel>

    override suspend fun getUsers(): Flow<Resource<List<User>>> = flow {

        try {
            userHelper.getUsers().let {
                if (it.isSuccessful) {
                    response = it.body()!!
                    emit(Resource.Success(response.map { userModel -> userModelMapper.mapFromDTO(userModel)  }))
                } else {
                    emit(Resource.Error(it.errorBody().toString()))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

}