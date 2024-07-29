package com.codeassessmentexample.data.local.mappers

import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.data.local.model.UserEntity
class UserEntityMapper: EntityMapper<User, UserEntity>
{
    override fun mapFromModel(model: User): UserEntity = UserEntity(id= model.id, name = model.name, email = model.email, avatar = model.avatar)
    override fun mapToModel(entity: UserEntity): User {
        return User(id = entity.id, name = entity.name, email = entity.email, avatar = entity.avatar)
    }
}