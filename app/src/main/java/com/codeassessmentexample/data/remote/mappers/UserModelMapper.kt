package com.codeassessmentexample.data.remote.mappers

import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.data.remote.modelDTO.UserModel
import javax.inject.Inject

class UserModelMapper @Inject constructor(): ModelMapper<UserModel, User> {
    override fun mapToModel(model: UserModel): User {
        return User(id = model.id, name = model.name, email = model.email, avatar = model.avatar)
    }
}