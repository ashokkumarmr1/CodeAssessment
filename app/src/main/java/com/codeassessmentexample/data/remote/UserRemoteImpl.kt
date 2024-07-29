package com.codeassessmentexample.data.remote

import com.codeassessmentexample.common.core.Resource
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.data.remote.api.UserApiService
import com.codeassessmentexample.data.remote.mappers.UserModelMapper
import com.codeassessmentexample.data.remote.modelDTO.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRemoteImpl  @Inject constructor(
    private val userHelper: UserApiService,
    private val userModelMapper: UserModelMapper
)
{
    lateinit var response : List<UserModel>

    suspend fun getUsers(): Flow<Resource<List<User>>> = flow {

        try {
            userHelper.getUsers().let {
                if (it.isSuccessful) {
                    response = it.body()!!
                    emit(Resource.Success(response.map { userModel -> userModelMapper.mapToModel(userModel)  }))
                } else {
                    emit(Resource.Error(it.errorBody().toString()))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

}