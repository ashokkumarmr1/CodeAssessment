package com.codeassessmentexample.remote.mappers

import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.remote.modelDTO.UserModel
import javax.inject.Inject

class UserModelMapper @Inject constructor(): ModelMapper<UserModel, User> {
    override fun mapFromDTO(model: UserModel): User {
        return User(id = model.id, name = model.name, email = model.email, avatar = model.avatar)
    }
}